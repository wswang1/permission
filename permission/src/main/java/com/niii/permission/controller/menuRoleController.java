package com.niii.permission.controller;

import com.niii.permission.common.Result;
import com.niii.permission.common.StatusCode;
import com.niii.permission.service.MenuRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "menurole",description = "角色->菜单服务")
@RestController
@RequestMapping(value = "/menurole")
@CrossOrigin(origins ="*" , maxAge = 3600)
public class menuRoleController {

    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation("分配菜单")
    @RequestMapping(method = RequestMethod.POST,value = "/assign")
    public Result assign(@RequestParam Integer roleId, @RequestBody List<Integer> menuList){
        menuRoleService.assign(roleId, menuList);
        return new Result(true,StatusCode.OK,"分配成功");
    }


}
