package com.niii.permission.service;

import com.niii.permission.common.JwtUtil;
import com.niii.permission.mapper.MenuMapper;
import com.niii.permission.mapper.MenuRoleMapper;
import com.niii.permission.mapper.UserRoleMapper;
import com.niii.permission.pojo.*;
import com.niii.permission.mapper.UserMapper;
import io.swagger.annotations.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class userService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Autowired
    private MenuMapper menuMapper;

    public void add(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());

        List<User> list= userMapper.selectByExample(userExample);
        if(list.size()>0){
            throw new RuntimeException("用户名已存在");
        }
        userMapper.insert(user);
    }

    public void delete(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }

    public List<User> findall(String username,String name,String telephone){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEnabledNotEqualTo(false);
        if(!StringUtils.isEmpty(username)){
            criteria.andUsernameLike("%"+username+"%");
        }
        if(!StringUtils.isEmpty(name)){
            criteria.andNameLike("%"+name+"%");
        }
        if(!StringUtils.isEmpty(telephone)){
            criteria.andTelephoneLike("%"+telephone+"%");
        }


        return userMapper.selectByExample(userExample);
    }

    public void update(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(user.getId());

        userMapper.updateByExampleSelective(user,userExample);
    }

    public String login(String userName, String passWord){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userName);

        List<User> list =userMapper.selectByExample(userExample);
        if(list.size()==0){
            throw new RuntimeException("用户名不存在");
        }
        User loginUser = list.get(0);
        if(!encoder.matches(passWord, loginUser.getPassword())){
            throw new RuntimeException("密码错误");
        }

        String token = jwtUtil.createJWT(loginUser.getId()+"", loginUser.getUsername(),"NIII",60*60*1000);

        return token;
    }

    public void resetPass(String newPass, Integer id){
        newPass = encoder.encode(newPass);
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(newPass);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);
        userMapper.updateByExampleSelective(user,userExample);
    }


    public List<UserRole> getRole(Integer id){
        UserRoleExample userRoleExample =new UserRoleExample();
        userRoleExample.createCriteria().andUseridEqualTo(id);
        return userRoleMapper.selectByExample(userRoleExample);
    }

    public List<Menu> getInfo(String token) {
        Integer id = Integer.valueOf(jwtUtil.parseJWT(token).getId());
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUseridEqualTo(id);
        List<UserRole> list = userRoleMapper.selectByExample(userRoleExample);
        if(list.size()==0){
            return new LinkedList<>();
        }
        MenuRoleExample menuRoleExample = new MenuRoleExample();

        for(UserRole userRole: list){
            MenuRoleExample.Criteria criteria = menuRoleExample.createCriteria();
            criteria.andRidEqualTo(userRole.getRid());
            menuRoleExample.or(criteria);
        }
        List<MenuRole> list2 = menuRoleMapper.selectByExample(menuRoleExample);
        if(list2.size()==0){
            return new LinkedList<>();
        }
        MenuExample menuExample = new MenuExample();
        for(MenuRole menuRole: list2){
            MenuExample.Criteria criteria = menuExample.createCriteria();
            criteria.andIdEqualTo(menuRole.getMid());
            menuExample.or(criteria);
        }

        return menuMapper.selectByExample(menuExample);

    }
}
