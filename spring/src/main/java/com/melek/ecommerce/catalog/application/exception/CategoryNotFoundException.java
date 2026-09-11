package com.melek.ecommerce.catalog.application.exception;

import com.melek.ecommerce.catalog.domain.model.CategoryId;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(CategoryId categoryId) {
        super("Category not found: " + categoryId.value());
    }
}
