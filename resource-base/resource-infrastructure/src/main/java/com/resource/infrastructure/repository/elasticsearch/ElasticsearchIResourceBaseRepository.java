package com.resource.infrastructure.repository.elasticsearch;

import com.resource.common.annotation.Domain;
import com.resource.configuration.MetaConfig;
import com.resource.core.domain.Document;
import com.resource.core.domain.Resource;
import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract class ElasticsearchIResourceBaseRepository<T extends Resource> extends RepositoryNameSpaceHandler implements IResourceBaseService<T>, IStorage {

    @Autowired
    private MetaConfig metaConfig;

    private final ElasticsearchRepository<T> elasticsearchRepository;
    private final Class<T> clazz;
    private String domain;

    public ElasticsearchIResourceBaseRepository(ReactiveElasticsearchClient client, Class<T> type) {
        this.clazz = type;
        this.elasticsearchRepository = new ElasticsearchRepository<>(client, clazz);
        Domain domain = type.getAnnotation(Domain.class);
        this.domain = domain.value();
    }

    @Override
    public String getStorage() {
        return String.format("t-resource-%s-%s", getNamespace(), getDomain());
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
        String table = getStorage();
        return elasticsearchRepository.getById(table, id);
    }

    @Override
    public Mono<Boolean> deleteById(String id) {
        String table = getStorage();
        return elasticsearchRepository.deleteDocument(table, id);
    }

    @Override
    public Mono<Boolean> save(T resource) {
        String table = getStorage();
        String id = UUID.randomUUID().toString();
        resource.setId(id);
        return elasticsearchRepository.save(table, id, resource);
    }

    @Override
    public Mono<Boolean> updateById(String id, T resource) {
        String table = getStorage();
        return elasticsearchRepository.save(table, id, resource);
    }
}
