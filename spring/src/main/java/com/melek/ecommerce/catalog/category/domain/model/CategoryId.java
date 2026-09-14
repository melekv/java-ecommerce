package com.melek.ecommerce.catalog.category.domain.model;

import java.util.UUID;

public record CategoryId(UUID value) {

    public CategoryId {
        if (value == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }
}
