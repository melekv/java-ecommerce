package com.melek.ecommerce.catalog.category.application;

import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;

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
