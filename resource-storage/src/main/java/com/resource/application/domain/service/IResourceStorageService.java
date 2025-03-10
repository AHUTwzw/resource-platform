package com.resource.application.domain.service;

import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceCoreService;
import reactor.core.publisher.Mono;

public interface IResourceStorageService<T extends Resource> extends IResourceCoreService<T> {
    Mono<Resource> upload(String namespace, Resource resourceReq);
}
