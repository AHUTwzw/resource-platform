package com.resource.security.domain.service;

import com.resource.security.domain.AccessKeyPermission;
import reactor.core.publisher.Mono;

public interface IAccessKeyService {
    Mono<AccessKeyPermission> registerAccessKey(AccessKeyPermission request);
}
