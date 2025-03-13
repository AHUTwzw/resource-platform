package com.resource.storage.infrastructure;

import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import com.resource.storage.domain.models.Storage;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class StorageHistoryRepository extends ElasticsearchIResourceHistoryRepository<Storage> {

    public StorageHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, Storage.class);
    }
}
