package com.resource.admin.domain.service.impl;

import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import com.resource.admin.domain.models.NameSpace;
import com.resource.admin.domain.service.INameSpaceService;
import org.springframework.stereotype.Service;

@Service
public class NameSpaceService extends ResourceCoreService<NameSpace> implements INameSpaceService {
    public NameSpaceService(IResourceBaseService<NameSpace> resourceBaseService, IResourceHistoryService<NameSpace> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }
}
