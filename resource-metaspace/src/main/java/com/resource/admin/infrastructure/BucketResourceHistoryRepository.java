package com.resource.admin.infrastructure;

import com.resource.admin.domain.models.Buckets;
import com.resource.admin.domain.models.NameSpace;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceHistoryRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class BucketResourceHistoryRepository extends ElasticsearchIResourceHistoryRepository<Buckets> {

    public BucketResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient, Buckets.class);
    }
}
