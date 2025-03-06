package com.resource.infrastructure.repository;

import com.resource.core.domain.Resource;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleResourceBaseRepository extends ElasticsearchIResourceBaseRepository<Resource> {

    public SimpleResourceBaseRepository(ReactiveElasticsearchClient client) {
        super(client, Resource.class);
    }
}
