package com.melek.ecommerce.catalog.product.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSearchResponse(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    String currency,
    UUID categoryId,
    float score
) {
}
