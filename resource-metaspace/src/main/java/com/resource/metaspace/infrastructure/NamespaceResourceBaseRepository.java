package com.resource.metaspace.infrastructure;

import com.resource.metaspace.domain.models.NameSpace;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class NamespaceResourceBaseRepository extends ElasticsearchIResourceBaseRepository<NameSpace> {

    public NamespaceResourceBaseRepository(ReactiveElasticsearchClient client) {
        super(client, NameSpace.class);
    }
}
