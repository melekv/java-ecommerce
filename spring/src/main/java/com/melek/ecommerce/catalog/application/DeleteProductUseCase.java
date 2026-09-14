package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;


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
