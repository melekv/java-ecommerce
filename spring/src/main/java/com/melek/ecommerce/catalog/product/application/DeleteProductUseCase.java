package com.melek.ecommerce.catalog.product.application;

import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;


public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(
        ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    public void execute(ProductId id) {
        productRepository.findById(id)
            .orElseThrow(
                () -> new ProductNotFoundException(id)
            );

        productRepository.delete(id);
    }
}
