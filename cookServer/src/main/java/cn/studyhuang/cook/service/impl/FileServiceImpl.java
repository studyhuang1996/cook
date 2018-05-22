package cn.studyhuang.cook.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import cn.studyhuang.cook.api.FTPUtils;
import cn.studyhuang.cook.service.IFileService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by huang on 2018/3/18.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private static  final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file , String path){
    	//获取名字
        String filename = file.getOriginalFilename();
        //扩展名
        String s = filename.substring(filename.lastIndexOf(".") + 1);
        //避免名字重复被覆盖
        String uploadFile = UUID.randomUUID().toString()+"."+s;

        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);//可写
            fileDir.mkdirs();
        }

         File fileTarget = new File(path,uploadFile);

        try {
            file.transferTo(fileTarget);
            //文件上传成功
            //上传到ftp
          FTPUtils.uploadFile(Lists.newArrayList(fileTarget));
            //删除upload文件夹下的图片
            fileTarget.delete();
        } catch (IOException e) {
            e.printStackTrace();
        //    logger.error("文件上传失败",e);
            return  null;
        }

        return fileTarget.getName();
    }
}
