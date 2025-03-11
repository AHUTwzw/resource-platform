package com.resource.infrastructure.repository.elasticsearch;

import com.resource.core.domain.Resource;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository<T extends Resource> extends ReactiveElasticsearchRepository<T, String> {
}
