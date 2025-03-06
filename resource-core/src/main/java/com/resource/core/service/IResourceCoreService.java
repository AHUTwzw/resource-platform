package com.resource.core.service;

import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceCoreService<T extends Resource> {
    Mono<T> findById(String namespace, String business, String id, String version);
    Mono<Boolean> deleteById(String namespace, String business, String id);
    Mono<T> findByUri(String namespace, String business, String id);
    Mono<T> save(String namespace, String business, T resource);
    Mono<T> updateById(String namespace, String business, String id, T resource);

}
