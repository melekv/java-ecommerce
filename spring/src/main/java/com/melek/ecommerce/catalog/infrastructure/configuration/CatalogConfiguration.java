package com.melek.ecommerce.catalog.infrastructure.configuration;

import com.melek.ecommerce.catalog.application.*;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfiguration {

    @Bean
    CreateProductUseCase createProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository
    ) {
        return new CreateProductUseCase(productRepository, categoryRepository);
    }

    @Bean
    GetProductUseCase getProductUseCase(
        ProductRepository productRepository
    ) {
        return new GetProductUseCase(productRepository);
    }

    @Bean
    GetProductsUseCase getProductsUseCase(
        ProductRepository productRepository
    ) {
        return new GetProductsUseCase(productRepository);
    }

    @Bean
    UpdateProductUseCase updateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository
    ) {
        return new UpdateProductUseCase(productRepository, categoryRepository);
    }

    @Bean
    DeleteProductUseCase deleteProductUseCase(
        ProductRepository productRepository
    ) {
        return new DeleteProductUseCase(productRepository);
    }

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
