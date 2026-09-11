package com.melek.ecommerce.catalog.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
    UUID id,
    String name,
    String description,
    BigDecimal price,
    String currency,
    UUID categoryId
) {
}
