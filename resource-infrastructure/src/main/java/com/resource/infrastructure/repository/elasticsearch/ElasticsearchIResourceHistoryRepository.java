package com.resource.infrastructure.repository.elasticsearch;

import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceHistoryService;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract class ElasticsearchIResourceHistoryRepository<T extends Resource> implements IResourceHistoryService<T> {
    private final Class<T> type;

    private final ReactiveElasticsearchClient client;

    public ElasticsearchIResourceHistoryRepository(ReactiveElasticsearchClient elasticsearchClient, Class<T> type) {
        this.type = type;
        this.client = elasticsearchClient;
    }

    @Override
    public Mono<T> findById(String index, String id) {
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
    public Mono<T> save(String index, T resource) {
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
