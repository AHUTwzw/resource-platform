package com.resource.metaspace.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.metaspace.domain.models.NameSpace;
import com.resource.metaspace.domain.service.impl.NameSpaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/namespace/")
public class NameSpaceEndpoint extends AbstractResourceCrudEndpoint<NameSpace, NameSpaceService> {
    public NameSpaceEndpoint(NameSpaceService resourceCoreService) {
        super(resourceCoreService);
    }
}
