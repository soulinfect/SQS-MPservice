package com.example.localstack.localstackdemo.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final S3Client s3Client;
    private final String bucketName;

    public void uploadTextFile(String fileName, String content) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType("text/plain")
                .build();

        s3Client.putObject(request, RequestBody.fromString(content));
        logger.info("Файл {} загружен в S3 бакет {}", fileName, bucketName);
    }
}
