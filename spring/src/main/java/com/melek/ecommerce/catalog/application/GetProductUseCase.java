package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;

public class GetProductUseCase {

    private final ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(ProductId id) {
        return productRepository.findById(id)
            .orElseThrow(
                () -> new ProductNotFoundException(id)
            );
    }
}
