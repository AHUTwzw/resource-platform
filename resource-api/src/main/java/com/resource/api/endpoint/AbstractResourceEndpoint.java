package com.resource.api.endpoint;

import com.resource.common.RD;
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
 * business  业务线
 */
@RestController
public abstract class AbstractResourceEndpoint<T extends Resource, R extends ResourceCoreService<T>> extends AbstractEndpoint {

    private final IResourceCoreService<T> resourceService;

    public AbstractResourceEndpoint(IResourceCoreService<T> resourceCoreService) {
        this.resourceService = resourceCoreService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取一个资源对象", description = "通过id获取一个资源对象")
    public Mono<RD<T>> findById(@PathVariable String id,
                                @RequestParam(required = false) String version) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.findById(namespace, business, id, version));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除一个资源对象", description = "通过id删除获取一个资源对象")
    public Mono<RD<Boolean>> deleteById(@PathVariable String id) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.deleteById(namespace, business, id));
    }

    @PostMapping
    @Operation(summary = "保存一个资源对象", description = "保存一个资源对象")
    public Mono<RD<T>> saveOne(@RequestBody T resourceReq) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.save(namespace, business, resourceReq));
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新一个资源对象", description = "通过id更新获取一个资源对象")
    public Mono<RD<T>> update(@PathVariable String id, @RequestBody T resourceReq) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.updateById(namespace, business, id, resourceReq));
    }
}
