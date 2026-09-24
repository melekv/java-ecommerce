package com.melek.ecommerce.cart.domain.model;

import java.util.UUID;

public record CartId(
    UUID value
) {
    public CartId {
        if (value == null) {
            throw new IllegalArgumentException("Cart id cannot be null");
        }
    }

    public static CartId generate() {
        return new CartId(UUID.randomUUID());
    }
}
