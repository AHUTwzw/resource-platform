package com.resource.admin.infrastructure;

import com.resource.admin.domain.models.NameSpace;
import com.resource.core.domain.Resource;
import com.resource.infrastructure.repository.elasticsearch.ElasticsearchIResourceBaseRepository;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;

@Repository
public class NamespaceResourceBaseRepository extends ElasticsearchIResourceBaseRepository<NameSpace> {

    public NamespaceResourceBaseRepository(ReactiveElasticsearchClient client) {
        super(client, NameSpace.class);
    }
}
