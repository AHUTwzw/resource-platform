package com.resource.api.endpoint;

import com.resource.common.vo.RD;
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

    @GetMapping("/{uri:.+}")
    @Operation(summary = "获取一个资源对象", description = "通过id获取一个资源对象")
    public Mono<RD<T>> findHistoryById(@PathVariable String uri,
                                @RequestParam(required = false) String version) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.findByUri(namespace, business, uri, version));
    }

    @DeleteMapping("/{uri:.+}")
    @Operation(summary = "删除一个资源对象", description = "通过id删除获取一个资源对象")
    public Mono<RD<Boolean>> deleteById(@PathVariable String uri) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.deleteByUri(namespace, business, uri));
    }

    @PostMapping
    @Operation(summary = "保存一个资源对象", description = "保存一个资源对象")
    public Mono<RD<T>> saveOne(@RequestBody T resourceReq) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.save(namespace, business, resourceReq));
    }

    @PutMapping("/{uri:.+}")
    @Operation(summary = "更新一个资源对象", description = "通过id更新获取一个资源对象")
    public Mono<RD<T>> update(@PathVariable String uri, @RequestBody T resourceReq) {
        String namespace = getNameSpace();
        String business = getBusiness();
        return RD.ok(resourceService.updateByUri(namespace, business, uri, resourceReq));
    }
}
