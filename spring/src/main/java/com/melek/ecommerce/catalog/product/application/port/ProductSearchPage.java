package com.melek.ecommerce.catalog.product.application.port;

import java.util.List;

public record ProductSearchPage(
    List<ProductSearchResult> content,
    long totalElements,
    int page,
    int size
) {
}
