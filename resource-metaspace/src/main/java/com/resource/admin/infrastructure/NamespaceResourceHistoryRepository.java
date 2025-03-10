package com.resource.admin.infrastructure;

import com.resource.admin.domain.models.NameSpace;
import com.resource.core.domain.Resource;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class NamespaceResourceHistoryRepository extends ElasticsearchIResourceHistoryRepository<NameSpace> {

    public NamespaceResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, NameSpace.class);
    }
}
