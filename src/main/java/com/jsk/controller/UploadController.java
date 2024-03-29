package com.jsk.controller;

import com.jsk.pojo.Result;
import com.jsk.utils.AzureStorageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AzureStorageUtils azureStorageUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件上传, 文件名: {}", file.getOriginalFilename());

        //调用阿里云OSS工具类进行文件上传
        String url = azureStorageUtils.uploadToAzure(file);
        log.info("文件上传完成,文件访问的url: {}", url);

        return Result.success(url);
    }

}