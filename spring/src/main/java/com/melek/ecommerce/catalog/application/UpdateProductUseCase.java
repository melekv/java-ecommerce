package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.model.Money;
import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Currency;

public class UpdateProductUseCase {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public UpdateProductUseCase(
        ProductRepository productRepository,
        CategoryRepository categoryRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

        return productRepository.save(product);
    }
}
