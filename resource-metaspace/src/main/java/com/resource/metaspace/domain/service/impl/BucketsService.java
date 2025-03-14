package com.resource.metaspace.domain.service.impl;

import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import com.resource.metaspace.domain.models.Buckets;
import com.resource.metaspace.domain.service.IBucketsService;
import org.springframework.stereotype.Service;

@Service
public class BucketsService extends ResourceCoreService<Buckets> implements IBucketsService {
    public BucketsService(IResourceBaseService<Buckets> resourceBaseService,
                          IResourceHistoryService<Buckets> resourceHistoryService) {
        super(resourceBaseService, resourceHistoryService);
    }
}
