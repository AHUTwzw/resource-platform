package com.resource.api.endpoint;

import com.resource.common.vo.RD;
import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceCoreService;
import com.resource.core.service.ResourceCoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 *
 * 基础操作终端控制器
 * namespace 命名空间
 * bucket  桶
 */
@RestController
public abstract class AbstractResourceCrudEndpoint<T extends Resource, R extends ResourceCoreService<T>> extends AbstractEndpoint {

    private final IResourceCoreService<T> resourceService;

    public AbstractResourceCrudEndpoint(IResourceCoreService<T> resourceCoreService) {
        this.resourceService = resourceCoreService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取一个资源对象", description = "通过id获取一个资源对象")
    public Mono<RD<T>> findById(@PathVariable String id) {
        return RD.ok(resourceService.findById(id));
    }

    @GetMapping
    @Operation(summary = "获取一个资源对象", description = "通过uri获取一个资源对象")
    public Mono<RD<T>> findByUri(@RequestParam String uri,
                                @RequestParam(required = false) String version) {
        return RD.ok(resourceService.findByUri(uri, version));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除一个资源对象", description = "通过id删除一个资源对象")
    public Mono<RD<Boolean>> deleteById(@PathVariable String id) {
        return RD.ok(resourceService.deleteById(id));
    }

    @DeleteMapping
    @Operation(summary = "删除一个资源对象", description = "通过id删除一个资源对象")
    public Mono<RD<Boolean>> deleteByUri(@RequestParam String uri) {
        return RD.ok(resourceService.deleteByUri(uri));
    }

    @PostMapping
    @Operation(summary = "保存一个资源对象", description = "保存一个资源对象")
    public Mono<RD<T>> saveOne(@RequestBody T resourceReq) {
        return RD.ok(resourceService.save(resourceReq));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新一个资源对象", description = "通过id更新获取一个资源对象")
    public Mono<RD<T>> update(@PathVariable String id, @RequestBody T resourceReq) {
        return RD.ok(resourceService.updateById(id, resourceReq));
    }

    @PutMapping
    @Operation(summary = "更新一个资源对象", description = "通过id更新获取一个资源对象")
    public Mono<RD<T>> updateByUri(@PathVariable String uri, @RequestBody T resourceReq) {
        return RD.ok(resourceService.updateByUri(uri, resourceReq));
    }
}
