package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;

public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(CategoryId id, String name) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException(id));

        category.changeName(name);

        return categoryRepository.update(category);
    }
}
