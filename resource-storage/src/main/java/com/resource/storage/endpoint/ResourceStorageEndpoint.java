package com.resource.storage.endpoint;

import com.resource.api.endpoint.AbstractResourceCrudEndpoint;
import com.resource.common.vo.RD;
import com.resource.storage.domain.models.Storage;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.impl.ResourceStorageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/buckets/{buckets}/objects")
public class ResourceStorageEndpoint extends AbstractResourceCrudEndpoint<Storage, ResourceStorageService> {
    private final ResourceStorageService resourceCoreService;

    public ResourceStorageEndpoint(ResourceStorageService resourceCoreService) {
        super(resourceCoreService);
        this.resourceCoreService = resourceCoreService;
    }

    @PostMapping("/getUploadSts")
    @Operation(summary = "获取上传凭证", description = "获取上传凭证")
    public Mono<RD<UploadInfo>> getUploadSts(
            @PathVariable String buckets,
            @RequestBody Storage resourceReq) {
        return RD.ok(resourceCoreService.getUploadSts(buckets, resourceReq));
    }
}
