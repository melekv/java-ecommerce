package com.melek.ecommerce.catalog.domain.model;

import java.util.UUID;

public record ProductId(UUID value) {

    public ProductId {
        if (value == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }
}
