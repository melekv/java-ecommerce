package com.melek.ecommerce.catalog.domain.model;

import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
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
