package com.melek.ecommerce.cart.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
    UUID cartItemId,
    UUID productId,
    BigDecimal price,
    String currency,
    int quantity
) {
}
