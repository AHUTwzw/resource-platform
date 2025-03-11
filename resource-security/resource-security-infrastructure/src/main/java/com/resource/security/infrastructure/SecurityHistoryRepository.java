package com.resource.security.infrastructure;

import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import com.resource.security.core.domain.entity.AccessKeyPermission;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityHistoryRepository extends ElasticsearchIResourceHistoryRepository<AccessKeyPermission> {

    public SecurityHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, AccessKeyPermission.class);
    }
}
