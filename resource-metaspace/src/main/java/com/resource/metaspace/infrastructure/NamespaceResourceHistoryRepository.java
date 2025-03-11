package com.resource.metaspace.infrastructure;

import com.resource.metaspace.domain.models.NameSpace;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class NamespaceResourceHistoryRepository extends ElasticsearchIResourceHistoryRepository<NameSpace> {

    public NamespaceResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, NameSpace.class);
    }
}
