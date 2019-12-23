package com.niii.permission.controller;


import com.github.pagehelper.PageHelper;
import com.niii.permission.common.CommonPage;
import com.niii.permission.common.Result;
import com.niii.permission.common.StatusCode;
import com.niii.permission.pojo.User;
import com.niii.permission.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "user",description = "用户服务")
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins ="*" , maxAge = 3600)
public class userController {

    @Autowired
    private userService userService;


    @ApiOperation("添加用户")
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Result addUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Result(false, StatusCode.ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        userService.add(user);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @ApiOperation("更新用户")
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result updateUser(@Valid @RequestBody User user){

        userService.update(user);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @ApiOperation("删除用户")
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public Result deleteMenu(@RequestParam Integer id){
        userService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @ApiOperation("查找用户-分页")
    @RequestMapping(method = RequestMethod.GET,value = "/findall")
    public Result findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNum,@RequestParam String username, @RequestParam String name, @RequestParam String telephone){
        try{
            PageHelper.startPage(pageNum,pageSize);
            return new Result(true, StatusCode.OK,"查询成功", CommonPage.restPage(userService.findall(username,name,telephone)));
        }
        catch (Exception e){
            return new Result(true, StatusCode.ERROR,"查询失败");
        }

    }


    @ApiOperation("登陆用户")
    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public Result login(@RequestParam String userName, @RequestParam String passWord){

//        userService.login(userName, passWord);
        return new Result(true, StatusCode.OK,"登陆成功",userService.login(userName, passWord));
    }


    @ApiOperation("重置密码")
    @RequestMapping(method = RequestMethod.POST,value = "/resetpass")
    public Result reset(@RequestParam String newPass, @RequestParam Integer id){
        userService.resetPass(newPass, id);
        return new Result(true, StatusCode.OK,"重置成功");
    }

    @ApiOperation("获取用户角色")
    @RequestMapping(method = RequestMethod.GET,value = "/getRole")
    public Result getRole(@RequestParam Integer id){
        //userService.resetPass(newPass, id);
        return new Result(true, StatusCode.OK,"查询成功",userService.getRole(id));
    }

    @ApiOperation("获取用户信息（目录等）")
    @RequestMapping(method = RequestMethod.GET,value = "/getInfo")
    public Result getInfo(@RequestParam String token){
        //userService.resetPass(newPass, id);
        return new Result(true, StatusCode.OK,"查询成功",userService.getInfo(token));
    }
}
