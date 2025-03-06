package com.resource.api.endpoint;

import com.resource.core.domain.Resource;
import com.resource.core.service.SimpleResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple/resource")
@Tag(name = "Simple API", description = "Simple的API")
public class SimpleResourceEndpoint extends AbstractResourceEndpoint<Resource, SimpleResourceService> {
    public SimpleResourceEndpoint(SimpleResourceService simpleResourceService) {
        super(simpleResourceService);
    }
}
