package com.jsk.utils;

import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class AzureStorageUtils {

    @Value("${azure.storage.connectionString}")
    private String connectionString;

    @Value("${azure.storage.containerName}")
    private String containerName;

    /**
     * 实现上传图片到 Azure Blob Storage
     */
    public String uploadToAzure(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 构建 BlobClientBuilder
        BlobClientBuilder blobClientBuilder = new BlobClientBuilder().connectionString(connectionString).containerName(containerName).blobName(fileName);

        // 上传文件到 Azure Blob Storage
        blobClientBuilder.buildClient().upload(inputStream, file.getSize(), true);

        // 获取 Blob 的 URL
        String blobUrl = blobClientBuilder.buildClient().getBlobUrl();

        return blobUrl; // 返回上传到 Azure Blob Storage 的 Blob 的 URL
    }
}
