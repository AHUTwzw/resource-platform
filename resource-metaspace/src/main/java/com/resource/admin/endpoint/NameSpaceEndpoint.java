package com.resource.admin.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.admin.domain.models.NameSpace;
import com.resource.admin.domain.service.impl.NameSpaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metaspace/namespace/")
public class NameSpaceEndpoint extends AbstractResourceCrudEndpoint<NameSpace, NameSpaceService> {
    public NameSpaceEndpoint(NameSpaceService resourceCoreService) {
        super(resourceCoreService);
    }
}
