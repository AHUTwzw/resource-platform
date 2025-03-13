package com.resource.core.service;

import com.resource.configuration.MetaConfig;
import com.resource.core.domain.Resource;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
@Data
public abstract class ResourceCoreService<T extends Resource> implements IResourceCoreService<T> {

    private final IResourceBaseService<T> resourceBaseService;
    private final IResourceHistoryService<T> resourceHistoryService;

    public ResourceCoreService(IResourceBaseService<T> resourceBaseService, IResourceHistoryService<T> resourceHistoryService) {
        this.resourceBaseService = resourceBaseService;
        this.resourceHistoryService = resourceHistoryService;
    }

    @Override
    public Mono<T> findById(String id) {
        return resourceBaseService.findById(id);
    }

    @Override
    public Mono<T> findByUri(String uri, String version) {
        if (StringUtils.hasText(version)) {
            return resourceHistoryService.findById(uri);
        } else {
            return resourceBaseService.findById(uri);
        }
    }

    @Override
    public Mono<Boolean> deleteById(String id) {
        return resourceBaseService.deleteById(id);
    }

    @Override
    public Mono<Boolean> deleteByUri(String uri) {
        return resourceBaseService.deleteById(uri);
    }

    @Override
    public Mono<T> save(T resource) {
        return resourceBaseService
                .save(resource)
                .map(res -> {
                    return resourceHistoryService.save(resource);
                })
                .map(res -> {
                    return resource;
                });
    }

    @Override
    public Mono<T> updateById(String id, T resource) {
        return findByUri(id, null)
                .map(res -> resourceBaseService.updateById(id, resource))
                .then(resourceHistoryService.save(resource))
                .thenReturn(resource);
    }

    @Override
    public Mono<T> updateByUri(String uri, T resource) {
        return findByUri(uri, null)
                .map(res -> resourceBaseService.updateById(uri, resource))
                .then(resourceHistoryService.save(resource))
                .thenReturn(resource);
    }
}
