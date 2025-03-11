package com.resource.security.core.domain.service.impl;

import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import com.resource.security.core.domain.entity.AccessKeyPermission;
import com.resource.security.core.domain.service.IAccessKeyService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccessKeyService extends ResourceCoreService<AccessKeyPermission> implements IAccessKeyService {

    public AccessKeyService(IResourceBaseService<AccessKeyPermission> resourceBaseService,
                            IResourceHistoryService<AccessKeyPermission> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }

    @SneakyThrows
    @Override
    public Mono<AccessKeyPermission> save(AccessKeyPermission resource) {
        // Generate access token
        resource.setRights(resource.getRights().genSecretKey2Rights());
        return super.save(resource);
    }

    @Override
    public Mono<AccessKeyPermission> updateByUri(String uri, AccessKeyPermission resource) {
        return findByUri(uri, null)
                .map(res -> getResourceBaseService().updateById(uri, resource))
                .then(getResourceHistoryService().save(resource))
                .thenReturn(resource);
    }
}
