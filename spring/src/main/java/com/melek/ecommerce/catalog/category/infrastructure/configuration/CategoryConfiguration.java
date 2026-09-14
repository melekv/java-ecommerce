package com.melek.ecommerce.catalog.category.infrastructure.configuration;

import com.melek.ecommerce.catalog.category.application.*;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfiguration {

    @Bean
    CreateCategoryUseCase createCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    GetCategoryUseCase getCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new GetCategoryUseCase(categoryRepository);
    }

    @Bean
    GetCategoriesUseCase getCategoriesUseCase(
        CategoryRepository categoryRepository
    ) {
        return new GetCategoriesUseCase(categoryRepository);
    }

    @Bean
    UpdateCategoryUseCase updateCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new UpdateCategoryUseCase(categoryRepository);
    }

    @Bean
    DeleteCategoryUseCase deleteCategoryUseCase(
        CategoryRepository categoryRepository
    ) {
        return new DeleteCategoryUseCase(categoryRepository);
    }
}
