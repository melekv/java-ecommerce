package com.melek.ecommerce.cart.domain.model;

import java.util.UUID;

public record CartItemId(
    UUID value
) {
    public CartItemId {
        if (value == null) {
            throw new IllegalArgumentException("Cart item id cannot be null");
        }
    }

    public static CartItemId generate() {
        return new CartItemId(UUID.randomUUID());
    }
}
