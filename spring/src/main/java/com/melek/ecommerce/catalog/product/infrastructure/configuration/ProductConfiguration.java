package com.melek.ecommerce.catalog.product.infrastructure.configuration;

import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.catalog.product.application.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

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
}
