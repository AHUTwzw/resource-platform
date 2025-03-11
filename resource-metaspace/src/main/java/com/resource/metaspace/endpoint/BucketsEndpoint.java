package com.resource.metaspace.endpoint;

import com.resource.metaspace.domain.models.Buckets;
import com.resource.metaspace.domain.service.impl.BucketsService;
import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/buckets/")
public class BucketsEndpoint extends AbstractResourceCrudEndpoint<Buckets, BucketsService> {
    public BucketsEndpoint(BucketsService bucketsService) {
        super(bucketsService);
    }
}
