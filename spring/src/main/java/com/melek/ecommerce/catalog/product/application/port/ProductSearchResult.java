package com.melek.ecommerce.catalog.product.application.port;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSearchResult(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    String currency,
    UUID categoryId,
    float score
) {
}
