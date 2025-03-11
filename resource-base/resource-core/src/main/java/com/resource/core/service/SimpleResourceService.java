package com.resource.core.service;

import com.resource.core.domain.Resource;
import org.springframework.stereotype.Service;

@Service
public class SimpleResourceService extends ResourceCoreService<Resource> {
    public SimpleResourceService(IResourceBaseService<Resource> resourceBaseService,
                                 IResourceHistoryService<Resource> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }
}
