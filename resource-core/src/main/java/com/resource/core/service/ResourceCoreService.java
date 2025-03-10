package com.resource.core.service;

import com.resource.core.domain.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public abstract class ResourceCoreService<T extends Resource> implements IResourceCoreService<T> {

    private final IResourceBaseService<T> resourceBaseService;
    private final IResourceHistoryService<T> resourceHistoryService;

    public ResourceCoreService(IResourceBaseService<T> resourceBaseService, IResourceHistoryService<T> resourceHistoryService) {
        this.resourceBaseService = resourceBaseService;
        this.resourceHistoryService = resourceHistoryService;
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
    public Mono<T> findByUri(String namespace, String business, String uri, String version) {
        if (StringUtils.hasText(version)) {
            String table = getStorage(namespace, business);
            return resourceHistoryService.findById(table, uri);
        } else {
            String table = getStorage(namespace, business);
            return resourceBaseService.findById(table, uri);
        }
    }

    @Override
    public Mono<Boolean> deleteByUri(String namespace, String business, String uri) {
        String table = getStorage(namespace, business);
        return resourceBaseService.deleteById(table, uri);
    }

    @Override
    public Mono<T> save(String namespace, String business, T resource) {
        String table = getStorage(namespace, business);
        return resourceBaseService
                .save(table, resource)
                .map(res -> {
                    return resourceHistoryService.save(getStorageHistory(namespace, business), resource);
                })
                .map(res -> {
                    return resource;
                });
    }

    @Override
    public Mono<T> updateByUri(String namespace, String business, String uri, T resource) {
        String table = getStorage(namespace, business);
        return findByUri(namespace, business, uri, null)
                .map(res -> resourceBaseService.updateById(table, uri, resource))
                .then(resourceHistoryService.save(table, resource))
                .thenReturn(resource);
    }
}
