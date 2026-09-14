package com.melek.ecommerce.catalog.product.application.exception;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(ProductId productId) {
        super("Product not found: " + productId.value());
    }
}
