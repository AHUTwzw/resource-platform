package com.resource.core.service;

import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceBaseService<T extends Resource> {
    Mono<T> findById(String id);
    Mono<Boolean> deleteById(String id);
    Mono<Boolean> save(T resource);
    Mono<Boolean> updateById(String id, T resource);

}
