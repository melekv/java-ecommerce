package com.melek.ecommerce.catalog.product.application.dto;

import java.util.List;

public record ProductSearchPageResponse(
    List<ProductSearchResponse> content,
    long totalElements,
    int page,
    int size
) {
}
