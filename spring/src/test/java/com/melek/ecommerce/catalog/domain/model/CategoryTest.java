package com.melek.ecommerce.catalog.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    @Test
    void Should_Not_Allow_Empty_Name() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Category(
                CategoryId.generate(),
                null
            )
        );
    }
}
