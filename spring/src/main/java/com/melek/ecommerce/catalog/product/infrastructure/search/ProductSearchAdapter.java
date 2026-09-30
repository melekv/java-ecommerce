package com.melek.ecommerce.catalog.product.infrastructure.search;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.melek.ecommerce.catalog.product.application.port.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSearchAdapter implements ProductSearch {

    private final ElasticsearchOperations elasticsearchOperations;

    public ProductSearchAdapter(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public ProductSearchPage search(ProductSearchCriteria criteria) {
        List<Query> filters = new ArrayList<>();

        if (criteria.categoryId() != null) {
            filters.add(
                Query.of(q -> q.term(
                    t -> t
                        .field("categoryId")
                        .value(criteria.categoryId().toString())
                ))
            );
        }

        if (criteria.minPrice() != null) {
            filters.add(
                Query.of(q -> q.range(
                    r -> r.number(
                        n -> n
                            .field("price")
                            .gte(criteria.minPrice().doubleValue())
                    )
                ))
            );
        }

        if (criteria.maxPrice() != null) {
            filters.add(
                Query.of(q -> q.range(
                    r -> r.number(
                        n -> n
                            .field("price")
                            .lte(criteria.maxPrice().doubleValue())
                    )
                ))
            );
        }

        var searchQueryBuilder = NativeQuery.builder()
            .withQuery(
                q -> q.bool(
                    b -> b.must(
                        m -> m.multiMatch(
                            mm -> mm
                                .query(criteria.query())
                                .fields("name^3", "description")
                        )
                    )
                    .filter(filters)
                )
            )
            .withPageable(
                PageRequest.of(
                    criteria.page(),
                    criteria.size()
                )
            );

        if (criteria.sort() == ProductSearchSort.PRICE_ASC) {
            searchQueryBuilder.withSort(
                s -> s.field(f -> f
                    .field("price")
                    .order(SortOrder.Asc)
                )
            );
        }

        if (criteria.sort() == ProductSearchSort.PRICE_DESC) {
            searchQueryBuilder.withSort(
                s -> s.field(f -> f
                    .field("price")
                    .order(SortOrder.Desc)
                )
            );
        }

        SearchHits<ProductDocument> hits = elasticsearchOperations.search(
            searchQueryBuilder.build(),
            ProductDocument.class
        );

        List<ProductSearchResult> results = hits
            .stream()
            .map(
                hit -> {
                    ProductDocument document = hit.getContent();

                    return new ProductSearchResult(
                        document.getId(),
                        document.getName(),
                        document.getDescription(),
                        document.getPrice(),
                        document.getCurrency(),
                        document.getCategoryId(),
                        hit.getScore()
                    );
                }
            )
            .toList();

        return new ProductSearchPage(
            results,
            hits.getTotalHits(),
            criteria.page(),
            criteria.size()
        );
    }
}
