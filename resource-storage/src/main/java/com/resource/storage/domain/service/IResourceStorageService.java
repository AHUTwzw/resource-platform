package com.resource.storage.domain.service;

import com.resource.storage.domain.models.UploadInfo;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceCoreService;
import reactor.core.publisher.Mono;

public interface IResourceStorageService<T extends Resource> extends IResourceCoreService<T> {
    Mono<UploadInfo> getUploadSts(String buckets, Resource resourceReq);

    Mono<Void> handleCallback(String objectKey);
}
