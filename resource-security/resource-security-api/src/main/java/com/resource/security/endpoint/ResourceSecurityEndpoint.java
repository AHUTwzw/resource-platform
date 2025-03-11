package com.resource.security.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.security.core.domain.entity.AccessKeyPermission;
import com.resource.security.core.domain.service.impl.AccessKeyService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/access-key")
public class ResourceSecurityEndpoint extends AbstractResourceCrudEndpoint<AccessKeyPermission, AccessKeyService> {

    public ResourceSecurityEndpoint(AccessKeyService resourceCoreService) {
        super(resourceCoreService);
    }
}
