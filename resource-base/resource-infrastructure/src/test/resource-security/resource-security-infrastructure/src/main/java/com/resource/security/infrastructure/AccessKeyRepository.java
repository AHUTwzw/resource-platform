package com.resource.security.infrastructure;

import com.resource.core.domain.Resource;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class AccessKeyRepository extends ElasticsearchIResourceBaseRepository<Resource> {
    public AccessKeyRepository(ReactiveElasticsearchClient client, Class<Resource> type) {
        super(client, type);
    }
}