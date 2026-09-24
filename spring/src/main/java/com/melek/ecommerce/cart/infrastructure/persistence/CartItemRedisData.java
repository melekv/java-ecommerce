package com.melek.ecommerce.cart.infrastructure.persistence;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemRedisData(
    UUID id,
    UUID productId,
    BigDecimal price,
    String currency,
    int quantity
) {
}
