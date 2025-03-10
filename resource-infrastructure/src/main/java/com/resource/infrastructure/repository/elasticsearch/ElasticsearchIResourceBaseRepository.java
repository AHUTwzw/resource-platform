package com.resource.infrastructure.repository.elasticsearch;

import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceBaseService;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract class ElasticsearchIResourceBaseRepository<T extends Resource> implements IResourceBaseService<T> {

    private final ElasticsearchRepository<T> elasticsearchRepository;
    private final Class<T> clazz;

    public ElasticsearchIResourceBaseRepository(ReactiveElasticsearchClient client, Class<T> type) {
        this.clazz = type;
        this.elasticsearchRepository = new ElasticsearchRepository<>(client, clazz);
    }

    @Override
    public Mono<T> findById(String table, String id) {
        return elasticsearchRepository.getById(table, id);
    }

    @Override
    public Mono<Boolean> deleteById(String table, String id) {
        return elasticsearchRepository.deleteDocument(table, id);
    }

    @Override
    public Mono<Boolean> save(String table, T resource) {
        String id = UUID.randomUUID().toString();
        resource.setId(id);
        return elasticsearchRepository.save(table, id, resource);
    }

    @Override
    public Mono<Boolean> updateById(String table, String id, T resource) {
        return elasticsearchRepository.save(table, id, resource);
    }
}
