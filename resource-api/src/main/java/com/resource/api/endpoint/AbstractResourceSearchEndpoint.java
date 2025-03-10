package com.resource.api.endpoint;

import com.resource.core.domain.Resource;
import com.resource.core.service.ResourceCoreService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 基础操作终端控制器
 * namespace 命名空间
 * bucket  桶
 */
public abstract class AbstractResourceSearchEndpoint<T extends Resource, R extends ResourceCoreService<T>> extends AbstractEndpoint {

}
