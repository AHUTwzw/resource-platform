package com.resource.admin.infrastructure;

import com.resource.admin.domain.models.Buckets;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class BucketResourceBaseRepository extends ElasticsearchIResourceBaseRepository<Buckets> {

    public BucketResourceBaseRepository(ReactiveElasticsearchClient client) {
        super(client, Buckets.class);
    }
}
