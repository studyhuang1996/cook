package cn.studyhuang.cook.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by huang on 2018/3/18.
 */
public interface IFileService {
    String upload(MultipartFile file , String path);
}
