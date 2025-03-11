package com.resource.infrastructure.repository.elasticsearch;

import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.Result;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermRangeQuery;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class ElasticsearchRepository<T> {

    private final ReactiveElasticsearchClient client;
    private final Class<T> clazz;

    public ElasticsearchRepository(ReactiveElasticsearchClient client, Class<T> clazz) {
        this.client = client;
        this.clazz = clazz;
    }

    // 添加文档
    public Mono<T> addDocument(String index, String id, T document) {
        return client.index(request -> request
                .index(index)
                .id(id)
                .document(document)
        ).flatMap(response -> {
            if (response.result() == Result.Created) {
                return Mono.just(document);
            } else {
                return Mono.error(new RuntimeException("Failed to add document"));
            }
        });
    }

    /**
     * 批量插入文档
     *
     * @param index  索引名称
     * @param documents 文档列表（Map 形式）
     * @return 是否插入成功
     */
    public Mono<Boolean> bulkInsert(String index, List<Map<String, Object>> documents) {
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        for (Map<String, Object> doc : documents) {
            bulkRequest.operations(op -> op
                    .index(i -> i
                            .index(index)
                            .document(doc)));
        }
        return client.bulk(bulkRequest.build())
                .map(response -> !response.errors());
    }

    /**
     * 保存文档
     *
     * @param index  索引名称
     * @param id     文档 ID
     * @param entity 文档对象
     * @return 是否保存成功
     */
    public Mono<Boolean> save(String index, String id, T entity) {
        return client.index(indexRequest -> indexRequest
                .index(index)
                .refresh(Refresh.True)
                .id(id)
                .document(entity))
                .map(response -> response.result() == Result.Created || response.result() == Result.Updated);
    }


    /**
     * 根据 ID 查询文档
     *
     * @param index 索引名称
     * @param id    文档 ID
     * @return 查询结果
     */
    public Mono<T> getById(String index, String id) {
        return client.get(getRequest -> getRequest
                        .index(index)
                        .id(id), clazz)
                .flatMap(response -> {
                    if (response.found()) {
                        return Mono.just(response.source());
                    } else {
                        return Mono.error(new RuntimeException("Document not found"));
                    }
                });
    }

    // 更新文档
    public Mono<T> updateDocument(String index, String id, T document) {
        return client.update(request -> request
                .index(index)
                .id(id)
                .doc(document), clazz
        ).flatMap(response -> {
            if (response.result() == Result.Updated) {
                return Mono.just(document);
            } else {
                return Mono.error(new RuntimeException("Failed to update document"));
            }
        });
    }

    // 删除文档
    public Mono<Boolean> deleteDocument(String index, String id) {
        return client.delete(request -> request
                .index(index)
                .id(id)
        ).flatMap(response -> {
            if (response.result() == Result.Deleted) {
                return Mono.just(true);
            } else {
                return Mono.error(new RuntimeException("Failed to delete document"));
            }
        });
    }

    /**
     * 批量删除文档
     *
     * @param index 索引名称
     * @param ids   文档 ID 列表
     * @return 是否删除成功
     */
    public Mono<Boolean> bulkDelete(String index, List<String> ids) {
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();
        for (String id : ids) {
            bulkRequest.operations(op -> op
                    .delete(d -> d
                            .index(index)
                            .id(id)));
        }
        return client.bulk(bulkRequest.build())
                .map(response -> !response.errors());
    }

    /**
     * 条件查询
     *
     * @param index 索引名称
     * @param query 查询条件
     * @param clazz 返回类型
     * @return 查询结果列表
     */
    public Flux<T> query(String index, Query query, Class<T> clazz) {
        return Flux.from(client.search(searchRequest -> searchRequest
                        .index(index)
                        .query(query), clazz))
                .flatMap(response -> Flux.fromIterable(response.hits().hits()))
                .map(Hit::source);
    }

    /**
     * 搜索文档（匹配查询）
     *
     * @param index 索引名称
     * @param field 字段名称
     * @param value 字段值
     * @param clazz 返回类型
     * @return 查询结果
     */
    public Flux<T> search(String index, String field, String value, Class<T> clazz) {
        Query query = Query.of(q -> q.match(m -> m.field(field).query(value)));
        return query(index, query, clazz);
    }

    /**
     * 分页查询
     *
     * @param index 索引名称
     * @param query 查询条件
     * @param from  起始位置
     * @param size  每页大小
     * @return 查询结果
     */
    public Flux<T> paginate(String index, Query query, int from, int size) {
        return advancedQuery(index, query, from, size);
    }

    /**
     * 范围查询
     *
     * @param index 索引名称
     * @param field 字段名称
     * @param from  起始值
     * @param to    结束值
     * @return 查询结果
     */
    public Flux<T> rangeQuery(String index, String field, String from, String to) {
        Query query = Query.of(q -> q.range(r ->
                r.term(
                        TermRangeQuery.of(t ->
                                t.field(field)
                                        .gte(from)
                                        .lte(to)))
                )
        );
        return query(index, query, clazz);
    }

    /**
     * 模糊查询
     *
     * @param index 索引名称
     * @param field 字段名称
     * @param value 字段值
     * @return 查询结果
     */
    public Flux<T> fuzzyQuery(String index, String field, String value) {
        Query query = Query.of(q -> q.fuzzy(f -> f.field(field).value(value)));
        return query(index, query, clazz);
    }

    // 聚合查询 - 分组统计
    public Mono<Map<String, Aggregate>> aggregationGroupBy(String index, String field) {
        return client.search(searchRequest -> searchRequest
                .index(index)
                .aggregations(field, a -> a.terms(t -> t.field(field))), clazz)
                .map(ResponseBody::aggregations);
    }

    /**
     * 聚合查询 - 平均值
     *
     * @param index      索引名称
     * @param field      计算字段
     * @return 平均值结果
     */
    public Mono<Double> average(String index, String field) {
        return client.search(searchRequest -> searchRequest
                        .index(index)
                        .aggregations("average", a -> a.avg(avg -> avg.field(field))), clazz)
                .map(response -> response.aggregations().get("average").avg().value());
    }

    /**
     * 基础查询方法
     *
     * @param index 索引名称
     * @param query 查询条件
     * @return 查询结果
     */
    private Flux<T> query(String index, Query query) {
        return client.search(searchRequest -> searchRequest
                        .index(index)
                        .query(query), clazz)
                .flatMapMany(response -> Flux.fromIterable(response.hits().hits()))
                .map(Hit::source);
    }

    /**
     * 高级查询方法
     *
     * @param index 索引名称
     * @param query 查询条件
     * @param from  起始位置
     * @param size  每页大小
     * @return 查询结果
     */
    private Flux<T> advancedQuery(String index, Query query, int from, int size) {
        return client.search(searchRequest -> searchRequest
                        .index(index)
                        .query(query)
                        .from(from)
                        .size(size), clazz)
                .flatMapMany(response -> Flux.fromIterable(response.hits().hits()))
                .map(Hit::source);
    }

    // 将 Map 转换为泛型对象
    private T convertToObject(Map<String, Object> source, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(source, clazz);
    }
}