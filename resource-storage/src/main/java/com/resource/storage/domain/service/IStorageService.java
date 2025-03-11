package com.resource.storage.domain.service;

import com.resource.storage.domain.models.UploadInfo;
import reactor.core.publisher.Mono;

public interface IStorageService {
    Mono<UploadInfo> generateUploadInfo(String bucketName, String objectKey, int expirationMinutes);
}
