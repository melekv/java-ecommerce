package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.product.application.dto.ProductSearchPageResponse;
import com.melek.ecommerce.catalog.product.application.dto.ProductSearchResponse;
import com.melek.ecommerce.catalog.product.application.port.ProductSearch;
import com.melek.ecommerce.catalog.product.application.port.ProductSearchCriteria;
import com.melek.ecommerce.catalog.product.application.port.ProductSearchPage;
import com.melek.ecommerce.catalog.product.application.port.ProductSearchSort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SearchProductUseCase {

    private final ProductSearch productSearch;

    public SearchProductUseCase(ProductSearch productSearch) {
        this.productSearch = productSearch;
    }

    public ProductSearchPageResponse execute(
        String query,
        UUID categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        ProductSearchSort sort,
        int page,
        int size
    ) {
        ProductSearchCriteria criteria = new ProductSearchCriteria(
            query,
            categoryId,
            minPrice,
            maxPrice,
            sort,
            page,
            size
        );

        ProductSearchPage pageResult = productSearch.search(criteria);

        List<ProductSearchResponse> coontent = pageResult.content()
            .stream()
            .map(result -> new ProductSearchResponse(
                result.id(),
                result.name(),
                result.description(),
                result.price(),
                result.currency(),
                result.categoryId(),
                result.score()
            ))
            .toList();

        return new ProductSearchPageResponse(
            coontent,
            pageResult.totalElements(),
            pageResult.page(),
            pageResult.size()
        );
    }
}
