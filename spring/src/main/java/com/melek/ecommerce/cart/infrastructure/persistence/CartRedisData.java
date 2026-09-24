package com.melek.ecommerce.cart.infrastructure.persistence;

import java.util.List;
import java.util.UUID;

public record CartRedisData(
    UUID id,
    UUID customerId,
    List<CartItemRedisData> items
) {
}
