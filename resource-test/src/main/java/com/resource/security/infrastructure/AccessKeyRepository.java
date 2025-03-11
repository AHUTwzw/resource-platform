package com.resource.security.infrastructure;

import com.resource.security.domain.AccessKeyPermission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccessKeyRepository extends ReactiveCrudRepository<AccessKeyPermission, String> {
    Mono<AccessKeyPermission> findByAccessKey(String accessKey);
}