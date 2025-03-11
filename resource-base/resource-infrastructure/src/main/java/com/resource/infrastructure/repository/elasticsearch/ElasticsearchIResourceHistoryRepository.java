package com.resource.infrastructure.repository.elasticsearch;

import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.resource.common.annotation.Domain;
import com.resource.configuration.MetaConfig;
import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.IStorage;
import io.micrometer.common.util.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
@Data
public abstract class ElasticsearchIResourceHistoryRepository<T extends Resource> implements IResourceHistoryService<T>, IStorage {

    @Autowired
    private MetaConfig metaConfig;

    private final Class<T> type;
    private final ReactiveElasticsearchClient client;
    private String domain;

    public ElasticsearchIResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient, Class<T> type) {
        this.type = type;
        this.client = elasticsearchClient;
        Domain domain = type.getAnnotation(Domain.class);
        this.domain = domain.value();
    }

    @Override
    public String getStorage() {
        return String.format("t-resource-%s-%s-history", getNamespace(), getDomain());
    }

    @Override
    public String getNamespace() {
        return metaConfig.getNamespace();
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public Mono<T> findById(String id) {
        String index = getStorage();
        // 构造查询请求
        Mono<GetResponse<T>> response = client.get(b -> b
                        .index(index)
                        .id(id),
                type
        );
        return response.flatMap(res -> {
            // 检查文档是否存在
            if (res.found()) {
                return Mono.justOrEmpty(res.source());
            }
            return Mono.empty();
        });
    }

    @Override
    public Mono<T> save(T resource) {
        String index = getStorage();
        // 构造索引请求
        IndexRequest<T> request = IndexRequest.of(b -> b
                .index(index)
                .refresh(Refresh.True)
                .id(UUID.randomUUID().toString())
                .document(resource)
        );
        Mono<IndexResponse> indexResponseMono = client.index(request);
        return indexResponseMono.flatMap(indexResponse -> {
            return Mono.justOrEmpty(resource);
        });
    }
}
