package com.melek.ecommerce.catalog.product.infrastructure.configuration;

import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.catalog.product.application.*;
import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.catalog.product.infrastructure.messaging.RabbitMqProductEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    public CreateProductUseCase createProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        ProductEventPublisher productEventPublisher
    ) {
        return new CreateProductUseCase(
            productRepository,
            categoryRepository,
            productEventPublisher
        );
    }

    @Bean
    public GetProductUseCase getProductUseCase(
        ProductRepository productRepository
    ) {
        return new GetProductUseCase(productRepository);
    }

    @Bean
    public GetProductsUseCase getProductsUseCase(
        ProductRepository productRepository
    ) {
        return new GetProductsUseCase(productRepository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        ProductEventPublisher productEventPublisher
    ) {
        return new UpdateProductUseCase(
            productRepository,
            categoryRepository,
            productEventPublisher
        );
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(
        ProductRepository productRepository,
        ProductEventPublisher productEventPublisher
    ) {
        return new DeleteProductUseCase(productRepository, productEventPublisher);
    }

    @Bean
    public ProductEventPublisher productEventPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqProductEventPublisher(rabbitTemplate);
    }
}
