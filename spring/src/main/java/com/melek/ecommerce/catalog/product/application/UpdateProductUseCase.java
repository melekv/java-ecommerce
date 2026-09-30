package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.product.application.port.ProductEventPublisher;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.shared.messaging.event.ProductUpdatedEvent;

import java.math.BigDecimal;
import java.util.Currency;

public class UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductEventPublisher productEventPublisher;

    public UpdateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository,
        ProductEventPublisher productEventPublisher
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productEventPublisher = productEventPublisher;
    }

    public Product execute(
        ProductId id,
        String name,
        String description,
        BigDecimal price,
        Currency currency,
        CategoryId categoryId
    ) {
        Product product = productRepository.findById(id)
            .orElseThrow(
                () -> new ProductNotFoundException(id)
            );

        categoryRepository.findById(categoryId)
            .orElseThrow(
                () -> new CategoryNotFoundException(categoryId)
            );

        Money money = Money.of(price, currency);

        product.changeName(name);
        product.changeDescription(description);
        product.changePrice(money);
        product.changeCategory(categoryId);

        Product updatedProduct = productRepository.update(product);

        productEventPublisher.publish(
            new ProductUpdatedEvent(
                updatedProduct.getId().value()
            )
        );

        return updatedProduct;
    }
}
