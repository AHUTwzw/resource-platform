package com.resource.storage.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.impl.ResourceStorageService;
import com.resource.common.vo.RD;
import com.resource.core.domain.Resource;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController("/api/v1/buckets/{buckets}/objects")
public class ResourceStorageEndpoint extends AbstractResourceCrudEndpoint<Resource, ResourceStorageService> {
    private final ResourceStorageService resourceCoreService;

    public ResourceStorageEndpoint(ResourceStorageService resourceCoreService) {
        super(resourceCoreService);
        this.resourceCoreService = resourceCoreService;
    }

    @PostMapping("/getUploadSts")
    @Operation(summary = "上传一个资源对象", description = "保存一个资源对象")
    public Mono<RD<UploadInfo>> getUploadSts(
            @PathVariable String buckets,
            @RequestBody Resource resourceReq) {
        return RD.ok(resourceCoreService.getUploadSts(buckets, resourceReq));
    }

    @PostMapping("/callback")
    public Mono<Void> handleCallback(@RequestParam String objectKey) {
        return resourceCoreService.handleCallback(objectKey);
    }
}
