package com.niii.permission.controller;

import com.niii.permission.common.Result;
import com.niii.permission.common.StatusCode;
import com.niii.permission.service.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "roleuser",description = "角色->用户服务")
@RestController
@RequestMapping(value = "/roleuser")
@CrossOrigin(origins ="*" , maxAge = 3600)
public class roleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @ApiOperation("分配角色")
    @RequestMapping(method = RequestMethod.POST, value = "/assign")
    public Result assign(@RequestParam Integer id, @RequestBody List<Integer> roleList){
        roleUserService.assign(id,roleList);
        return new Result(true, StatusCode.OK,"分配成功");
    }

}
