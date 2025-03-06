package com.resource.infrastructure.repository;

import com.resource.core.domain.Resource;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SimpleResourceHistoryRepository extends ElasticsearchIResourceHistoryRepository<Resource> {

    public SimpleResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, Resource.class);
    }
}
