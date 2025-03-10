package com.resource.application.domain.service;

import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResourceStorageService extends ResourceCoreService<Resource> implements IResourceStorageService<Resource> {
    public ResourceStorageService(IResourceBaseService<Resource> resourceBaseService, IResourceHistoryService<Resource> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }

    @Override
    public String getStorage(String namespace, String bucket) {
        return String.format("t-%s-%s", namespace, bucket);
    }

    @Override
    public String getStorageHistory(String namespace, String bucket) {
        return String.format("t-%s-%s-history", namespace, bucket);
    }

    @Override
    public Mono<Resource> upload(String namespace, Resource resourceReq) {
        return Mono.empty();
    }
}
