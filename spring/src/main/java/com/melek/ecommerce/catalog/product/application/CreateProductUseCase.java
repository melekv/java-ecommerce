package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
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

    public CreateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

        return productRepository.save(product);
    }
}
