package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;

public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(CategoryId id) {
        return categoryRepository.findById(id)
            .orElseThrow(
                () -> new CategoryNotFoundException(id)
            );
    }
}
