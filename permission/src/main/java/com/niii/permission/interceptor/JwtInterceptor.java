package com.niii.permission.interceptor;

import com.niii.permission.common.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    private String id;

    private String username;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");

        String header = request.getHeader("Authorization");
//        System.out.println("123"+header);
        System.out.println(header);
        if(header==null || "".equals(header)||!header.startsWith("NIII")){
            throw new RuntimeException("令牌不存在");
        }
        header = header.substring(4);

        try{
            if(!jwtUtil.parseJWT(header).get("code").equals("NIII")){
                throw new RuntimeException("令牌有误");
            }
            id = jwtUtil.parseJWT(header).getId();
            username = jwtUtil.parseJWT(header).getSubject();

        }catch (Exception e){
            throw new RuntimeException("令牌过期或者有误");
        }
        System.out.println(jwtUtil.parseJWT(header).getExpiration());
        if(jwtUtil.parseJWT(header).getExpiration().getTime()-new Date().getTime()<10*60*1000){
            response.setHeader("Authorization", jwtUtil.createJWT(id, username,"NIII",60*60*1000));
        }






        return true;
    }
}
