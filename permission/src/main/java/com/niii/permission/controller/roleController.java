package com.niii.permission.controller;


import com.github.pagehelper.PageHelper;
import com.niii.permission.common.CommonPage;
import com.niii.permission.common.Result;
import com.niii.permission.common.StatusCode;
import com.niii.permission.pojo.Role;
import com.niii.permission.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "role",description = "角色服务")
@RestController
@RequestMapping(value = "/role")
@CrossOrigin(origins ="*" , maxAge = 3600)
public class roleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("新增角色")
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Result add(@RequestBody Role role){
        roleService.add(role);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @ApiOperation("更新角色")
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result update(@RequestBody Role role){
        roleService.update(role);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @ApiOperation("删除角色")
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public Result add(@RequestParam Integer id){
        roleService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @ApiOperation("查询角色")
    @RequestMapping(method = RequestMethod.GET,value = "/findAll")
    public Result add(){
        return new Result(true, StatusCode.OK, "查询成功", roleService.find());
    }

}
