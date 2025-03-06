package com.resource.core.service;

import com.resource.core.domain.Resource;
import reactor.core.publisher.Mono;

public interface IResourceBaseService<T extends Resource> {
    Mono<T> findById(String table,String id);
    Mono<Boolean> deleteById(String table,String id);
    Mono<Boolean> save(String table, T resource);
    Mono<Boolean> updateById(String table, String id, T resource);

}
