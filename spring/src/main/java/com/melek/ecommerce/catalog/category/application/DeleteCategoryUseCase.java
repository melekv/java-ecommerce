package com.melek.ecommerce.catalog.category.application;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;

public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void execute(CategoryId id) {
        categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryNotFoundException(id));

        categoryRepository.delete(id);
    }
}
