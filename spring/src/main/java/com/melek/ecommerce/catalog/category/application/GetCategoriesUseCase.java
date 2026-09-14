package com.melek.ecommerce.catalog.category.application;

import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;

import java.util.List;

public class GetCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> execute() {
        return categoryRepository.findAll();
    }
}
