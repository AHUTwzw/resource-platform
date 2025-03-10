package com.resource.application.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.application.domain.service.ResourceStorageService;
import com.resource.common.vo.RD;
import com.resource.core.domain.Resource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/storage/v1/{namespace}/")
public class ResourceStorageEndpoint extends AbstractResourceCrudEndpoint<Resource, ResourceStorageService> {
    private final ResourceStorageService resourceCoreService;

    public ResourceStorageEndpoint(ResourceStorageService resourceCoreService) {
        super(resourceCoreService);
        this.resourceCoreService = resourceCoreService;
    }

    @PostMapping("/upload")
    @Operation(summary = "上传一个资源对象", description = "保存一个资源对象")
    public Mono<RD<Resource>> upload(
            @PathVariable String namespace,
            @RequestBody Resource resourceReq) {
        return RD.ok(resourceCoreService.upload(namespace, resourceReq));
    }
}
