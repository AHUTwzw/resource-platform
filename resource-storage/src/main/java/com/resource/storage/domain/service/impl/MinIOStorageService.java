package com.resource.storage.domain.service.impl;

import com.resource.storage.domain.models.OSSMetadata;
import com.resource.storage.domain.models.STSInfo;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.IStorageService;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import reactor.core.publisher.Mono;

import java.util.Date;

public class MinIOStorageService implements IStorageService {

    private final String endpoint;
    private final String accessKey;
    private final String secretKey;

    public MinIOStorageService(String endpoint, String accessKey, String secretKey) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    @Override
    public Mono<UploadInfo> generateUploadInfo(String bucketName, String objectKey, int expirationMinutes) {
        return Mono.fromCallable(() -> {
            // Generate STS token (MinIO uses temporary credentials)
            STSInfo stsInfo = generateSTSToken(expirationMinutes);

            // Generate upload URL
            String uploadUrl = generatePresignedUrl(bucketName, objectKey, expirationMinutes);

            // Create MinIO metadata
            OSSMetadata ossMetadata = new OSSMetadata(bucketName, objectKey, endpoint);

            // Build UploadInfo
            return new UploadInfo(stsInfo, uploadUrl, ossMetadata);
        });
    }

    private STSInfo generateSTSToken(int expirationMinutes) {
        // MinIO does not have a built-in STS service, so we simulate it using temporary credentials
        return new STSInfo(
                accessKey, // Temporary access key
                secretKey, // Temporary secret key
                "minio-temporary-token", // Simulated security token
                new Date(System.currentTimeMillis() + expirationMinutes * 60 * 1000).toString() // Expiration
        );
    }

    private String generatePresignedUrl(String bucketName, String objectKey, int expirationMinutes) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();
            GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                    .method(Method.PUT)
                    .bucket(bucketName)
                    .object(objectKey)
                    .expiry(expirationMinutes * 60)
                    .build();
            // Generate presigned URL for PUT request
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate presigned URL", e);
        }
    }
}