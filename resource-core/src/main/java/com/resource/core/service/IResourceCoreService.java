package com.resource.core.service;

import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceCoreService<T extends Resource> extends IStorage {
    Mono<T> findByUri(String namespace, String business, String uri, String version);
    Mono<Boolean> deleteByUri(String namespace, String business, String uri);
    Mono<T> save(String namespace, String business, T resource);
    Mono<T> updateByUri(String namespace, String business, String uri, T resource);

}
