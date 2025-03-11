package com.resource.security.domain.service.impl;

import com.resource.security.domain.AccessKeyPermission;
import com.resource.security.domain.service.IAccessKeyService;
import com.resource.security.infrastructure.AccessKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AccessKeyService implements IAccessKeyService {

    @Autowired
    private AccessKeyRepository accessKeyRepository;

    public Mono<AccessKeyPermission> registerAccessKey(AccessKeyPermission request) {
        // Generate access token
        String accessToken = generateAccessToken();

        // Set access token
        request.(accessToken);

        // Save to database
        return accessKeyRepository.save(request);
    }

    private String generateAccessToken() {
        // Generate a secure random token
        return UUID.randomUUID().toString();
    }
}
