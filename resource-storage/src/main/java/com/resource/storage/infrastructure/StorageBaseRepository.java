package com.resource.storage.infrastructure;

import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import com.resource.storage.domain.models.Storage;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class StorageBaseRepository extends ElasticsearchIResourceBaseRepository<Storage> {

    public StorageBaseRepository(ReactiveElasticsearchClient client) {
        super(client, Storage.class);
    }

    @Override
    public String getNamespace() {
        return "storage";
    }
}
