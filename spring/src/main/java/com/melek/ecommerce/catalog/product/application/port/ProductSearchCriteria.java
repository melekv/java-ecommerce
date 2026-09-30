package com.melek.ecommerce.catalog.product.application.port;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSearchCriteria(
    String query,
    UUID categoryId,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    ProductSearchSort sort,
    int page,
    int size
) {
}
