package com.resource.security.endpoint;

import com.resource.security.domain.AccessKeyPermission;
import com.resource.security.domain.service.IAccessKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/access-key")
public class AccessKeyEndpoint {

    @Autowired
    private IAccessKeyService accessKeyService;

    @PostMapping("/register")
    public Mono<AccessKeyPermission> registerAccessKey(@RequestBody AccessKeyPermission request) {
        return accessKeyService.registerAccessKey(request);
    }
}