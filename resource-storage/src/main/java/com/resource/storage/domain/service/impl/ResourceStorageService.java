package com.resource.storage.domain.service.impl;

import com.resource.configuration.MetaConfig;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.IResourceStorageService;
import com.resource.storage.domain.service.IStorageService;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResourceStorageService extends ResourceCoreService<Resource> implements IResourceStorageService<Resource> {
    private final IStorageService storageService;

    public ResourceStorageService(IResourceBaseService<Resource> resourceBaseService,
                                  IResourceHistoryService<Resource> resourceHistoryService,
                                  IStorageService storageService) {
        super(resourceBaseService, resourceHistoryService);
        this.storageService = storageService;
    }

    @Override
    public Mono<UploadInfo> getUploadSts(String buckets, Resource resourceReq) {
        return storageService.generateUploadInfo(buckets,resourceReq.getTitle(), 60)
                .doOnNext(uploadInfo -> {
                    resourceReq.setUri(uploadInfo.getUploadUrl());
                    resourceReq.setIdentifier(uploadInfo.getUploadUrl());
                    super.save(resourceReq);
                });
    }

    @Override
    public Mono<Void> handleCallback(String objectKey) {
        return null;
    }
}
