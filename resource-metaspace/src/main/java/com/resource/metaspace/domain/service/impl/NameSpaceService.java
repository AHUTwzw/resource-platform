package com.resource.metaspace.domain.service.impl;

import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import com.resource.metaspace.domain.models.NameSpace;
import com.resource.metaspace.domain.service.INameSpaceService;
import org.springframework.stereotype.Service;

@Service
public class NameSpaceService extends ResourceCoreService<NameSpace> implements INameSpaceService {
    public NameSpaceService(IResourceBaseService<NameSpace> resourceBaseService,
                            IResourceHistoryService<NameSpace> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }
}
