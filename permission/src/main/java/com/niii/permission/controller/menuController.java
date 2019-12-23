package com.niii.permission.controller;

import com.niii.permission.pojo.Menu;
import com.niii.permission.common.Result;
import com.niii.permission.common.StatusCode;
import com.niii.permission.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "menu",description = "菜单服务")
@RestController
@RequestMapping(value = "/menu")
@CrossOrigin(origins ="*" , maxAge = 3600)

public class menuController {

    @Autowired
    private MenuService menuService;



    @ApiOperation("添加目录")
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Result addMenu(@RequestBody Menu menu){
        menuService.add(menu);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @ApiOperation("修改目录")
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result updateMenu(@RequestBody Menu menu){
        menuService.update(menu);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @ApiOperation("删除目录")
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public Result deleteMenu(@RequestParam Integer id){
        menuService.delete(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @ApiOperation("获取所有目录")
    @RequestMapping(method = RequestMethod.GET,value = "/findall")
    public Result findall(){

        return new Result(true, StatusCode.OK,"查询成功",menuService.findall());
    }

    @ApiOperation("获取角色对应的目录")
    @RequestMapping(method = RequestMethod.GET,value = "/findbyrid")
    public Result findbyrid(Integer rid){

        return new Result(true, StatusCode.OK,"查询成功",menuService.findbyrid(rid));
    }



}
