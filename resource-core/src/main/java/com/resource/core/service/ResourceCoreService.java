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
    public Mono<T> findById(String namespace, String business, String id, String version) {
        if (StringUtils.hasText(version)) {
            String table = String.format("t-%s-%s-history", namespace, business);
            return resourceHistoryService.findById(table, id);
        } else {
            String table = String.format("t-%s-%s", namespace, business);
            return resourceBaseService.findById(table, id);
        }
    }

    @Override
    public Mono<Boolean> deleteById(String namespace, String business, String id) {
        String uri = String.format("t-%s-%s", namespace, business);
        return resourceBaseService.deleteById(uri, id);
    }

    @Override
    public Mono<T> findByUri(String namespace, String business, String id) {
        return null;
    }

    @Override
    public Mono<T> save(String namespace, String business, T resource) {
        String uri = String.format("t-%s-%s", namespace, business);
        return resourceBaseService
                .save(uri, resource)
                .map(res -> {
                    return resourceHistoryService.save(uri + "-history", resource);
                })
                .map(res -> {
                    return resource;
                });
    }

    @Override
    public Mono<T> updateById(String namespace, String business, String id, T resource) {
        String uri = String.format("t-%s-%s", namespace, business);
        return findById(namespace, business, id, null)
                .map(res -> resourceBaseService.updateById(uri, id, resource))
                .then(resourceHistoryService.save(uri, resource))
                .thenReturn(resource);
    }
}
