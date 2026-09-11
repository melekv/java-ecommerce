package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;

import java.util.List;

public class GetProductsUseCase {

    private final ProductRepository productRepository;

    public GetProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute() {
        return productRepository.findAll();
    }
}
