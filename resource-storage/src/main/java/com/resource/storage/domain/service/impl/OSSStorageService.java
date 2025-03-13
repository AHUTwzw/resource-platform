package com.resource.storage.domain.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.resource.storage.domain.models.OSSMetadata;
import com.resource.storage.domain.models.STSInfo;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.IStorageService;
import reactor.core.publisher.Mono;

import java.net.URL;
import java.util.Date;
import java.util.UUID;

public class OSSStorageService implements IStorageService {
    private final String endpoint;
    private final String accessKeyId;
    private final String accessKeySecret;
    private final String roleArn;
    private final String roleSessionName;

    public OSSStorageService(String endpoint, String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.roleArn = roleArn;
        this.roleSessionName = roleSessionName;
    }

    @Override
    public Mono<UploadInfo> generateUploadInfo(String bucketName, String objectKey, int expirationMinutes) {
        return Mono.fromCallable(() -> {
            // Generate STS token
            STSInfo stsInfo = generateSTSToken(expirationMinutes);

            // Generate upload URL
            String uploadUrl = generatePresignedUrl(bucketName, objectKey, expirationMinutes);

            // Create OSS metadata
            OSSMetadata ossMetadata = new OSSMetadata(bucketName, objectKey, endpoint);

            // Build UploadInfo
            return new UploadInfo(objectKey, stsInfo, uploadUrl, ossMetadata);
        });
    }

    private STSInfo generateSTSToken(int expirationMinutes) {
        try {
            String regionId = endpoint.substring(0, endpoint.indexOf(".")).replace("oss-", "");
            IClientProfile config = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);

            DefaultAcsClient client = new DefaultAcsClient(config);
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName == null ? UUID.randomUUID().toString() : roleSessionName);
            request.setDurationSeconds(expirationMinutes * 60L);

            AssumeRoleResponse response = client.getAcsResponse(request);
            AssumeRoleResponse.Credentials body = response.getCredentials();

            return new STSInfo(
                    body.getAccessKeyId(),
                    body.getAccessKeySecret(),
                    body.getSecurityToken(),
                    body.getExpiration()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate STS token", e);
        }
    }

    private String generatePresignedUrl(String bucketName, String objectKey, int expirationMinutes) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        Date expiration = new Date(System.currentTimeMillis() + (long) expirationMinutes * 60 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectKey);
        request.setExpiration(expiration);
        request.setMethod(com.aliyun.oss.HttpMethod.PUT);

        URL url = ossClient.generatePresignedUrl(request);
        ossClient.shutdown();

        return url.toString();
    }
}
