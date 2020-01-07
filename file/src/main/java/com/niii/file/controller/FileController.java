package com.niii.file.controller;


import com.niii.file.common.Result;
import com.niii.file.common.StatusCode;
import com.niii.file.pojo.File;
import com.niii.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "FileController",description = "文件")
@RestController
@RequestMapping(value = "/file")
@CrossOrigin(origins ="*" , maxAge = 3600)
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("上传文件")
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public Result addFile(File file, MultipartFile file1){
        System.out.println(file);
        try {
            return new Result(true, StatusCode.OK,"上传成功",fileService.addFile(file, file1));
        }
        catch (Exception e){
            return new Result(false,StatusCode.ERROR,"上传失败");
        }

    }


    @ApiOperation("删除文件")
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete")
    public Result deleteFile(Integer id){
        try {

            return new Result(true, StatusCode.OK,"删除成功",fileService.deleteFile(id));
        }
        catch (Exception e){
            return new Result(false,StatusCode.ERROR,"删除失败");
        }

    }


    @ApiOperation("修改文件")
    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public Result updateFile(File file){
        try {

            return new Result(true, StatusCode.OK,"修改成功",fileService.updateFile(file));
        }
        catch (Exception e){
            return new Result(false,StatusCode.ERROR,"修改失败");
        }

    }

    @ApiOperation("查询文件")
    @RequestMapping(method = RequestMethod.GET,value = "/find")
    public Result findFile(String username){
        try {

            return new Result(true, StatusCode.OK,"查询成功",fileService.findFile(username));
        }
        catch (Exception e){
            return new Result(false,StatusCode.ERROR,"查询失败");
        }

    }

}
