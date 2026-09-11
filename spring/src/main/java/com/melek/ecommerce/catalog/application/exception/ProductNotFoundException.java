package com.melek.ecommerce.catalog.application.exception;

import com.melek.ecommerce.catalog.domain.model.ProductId;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(ProductId productId) {
        super("Product not found: " + productId.value());
    }
}
