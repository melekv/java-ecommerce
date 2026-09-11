package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;

public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(String name) {
        Category category = new Category(
            CategoryId.generate(),
            name
        );

        return categoryRepository.save(category);
    }
}
