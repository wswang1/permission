package com.niii.file.service;

import com.niii.file.mapper.FileMapper;
import com.niii.file.pojo.File;
import com.niii.file.pojo.FileExample;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    public int addFile(File file, MultipartFile file1){
        System.out.println("1234");
        System.out.println(file1);
        String originalFilename = file1.getOriginalFilename();
        //扩展名

        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(extention);
        String fileNameNew = UUID.randomUUID()+extention;
        System.out.println(fileNameNew);
        java.io.File file2 = new java.io.File("/home/"+fileNameNew);
        try {
            file1.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取新上传文件的物理路径
        String newFilePath = file2.getAbsolutePath();

        try {
            //加载fastDFS客户端的配置 文件

            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            //文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", file.getFileName());
            //执行上传，将上传成功的存放在web服务器（本机）上的文件上传到 fastDFS
            String fileId = client.upload_file1(newFilePath,null, metaList);

            System.out.println("upload success. file id is: " + fileId);
//            entryMaterial.setCreateTime(new Date());

//            entryMaterial.setMaterialName(originalFilename);
            file.setFilePath(fileId);

            //通过调用service及dao将文件的路径存储到数据库中
            //...

            //关闭trackerServer的连接
            trackerServer.close();
            file2.delete();
            return fileMapper.insert(file);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            throw new RuntimeException("上传错误");
        }
    }

    public int deleteFile(Integer id){
        File file = fileMapper.selectByPrimaryKey(id);
        try {
            //加载fastDFS客户端的配置 文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);

            //创建tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //定义storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            System.out.println(file);
            client.delete_file1(file.getFilePath());
            return fileMapper.deleteByPrimaryKey(id);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("上传错误");
        }

    }

    public int updateFile(File file){
        return fileMapper.updateByPrimaryKeySelective(file);
    }

    public List<File> findFile(String username){
        FileExample fileExample = new FileExample();
        fileExample.createCriteria().andUsernameEqualTo(username);
        return fileMapper.selectByExample(fileExample);
    }


}
