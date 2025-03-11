package com.resource.core.service;

import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceCoreService<T extends Resource> {
    Mono<T> findByUri(String uri, String version);
    Mono<Boolean> deleteByUri(String uri);
    Mono<T> save(T resource);
    Mono<T> updateByUri(String uri, T resource);

}
