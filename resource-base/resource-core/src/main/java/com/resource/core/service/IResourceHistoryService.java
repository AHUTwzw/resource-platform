package com.resource.core.service;

import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceHistoryService<T extends Resource> {
    Mono<T> findById(String id);
    Mono<T> save(T resource);
}
