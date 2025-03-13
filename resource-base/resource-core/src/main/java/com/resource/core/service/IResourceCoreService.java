package com.resource.core.service;

import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceCoreService<T extends Resource> {
    Mono<T> findById(String id);
    Mono<T> findByUri(String uri, String version);
    Mono<Boolean> deleteById(String id);
    Mono<Boolean> deleteByUri(String uri);
    Mono<T> save(T resource);
    Mono<T> updateById(String id, T resource);
    Mono<T> updateByUri(String uri, T resource);

}
