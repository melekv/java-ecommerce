package com.melek.ecommerce.catalog.category.application.exception;

import com.melek.ecommerce.catalog.category.domain.model.CategoryId;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(CategoryId categoryId) {
        super("Category not found: " + categoryId.value());
    }
}
