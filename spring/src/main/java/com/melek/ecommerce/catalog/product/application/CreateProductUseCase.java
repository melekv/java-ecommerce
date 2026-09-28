package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.shared.messaging.event.ProductCreatedEvent;
import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Currency;

public class CreateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductEventPublisher productEventPublisher;

    public CreateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        ProductEventPublisher productEventPublisher
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productEventPublisher = productEventPublisher;
    }

    public Product execute(
        String name,
        String description,
        BigDecimal price,
        Currency currency,
        CategoryId categoryId
    ) {
        categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new CategoryNotFoundException(categoryId)
            );

        Money money = Money.of(price, currency);

        Product product = new Product(
            ProductId.generate(),
            name,
            description,
            money,
            categoryId
        );

        Product savedProduct = productRepository.save(product);

        productEventPublisher.publish(
            new ProductCreatedEvent(
                savedProduct.getId().value()
            )
        );

        return savedProduct;
    }
}
