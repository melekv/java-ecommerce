package com.melek.ecommerce.cart.application.dto;

import java.util.List;
import java.util.UUID;

public record CartResponse(
    UUID cartId,
    UUID customerId,
    List<CartItemResponse> items
) {
}
