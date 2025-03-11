package com.resource.security.infrastructure;

import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import com.resource.security.core.domain.entity.AccessKeyPermission;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityBaseRepository extends ElasticsearchIResourceBaseRepository<AccessKeyPermission> {

    public SecurityBaseRepository(ReactiveElasticsearchClient client) {
        super(client, AccessKeyPermission.class);
    }
}
