package com.resource.admin.endpoint;

import com.resource.admin.domain.models.Buckets;
import com.resource.admin.domain.service.impl.BucketsService;
import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metaspace/buckets/")
public class BucketsEndpoint extends AbstractResourceCrudEndpoint<Buckets, BucketsService> {
    public BucketsEndpoint(BucketsService bucketsService) {
        super(bucketsService);
    }
}
