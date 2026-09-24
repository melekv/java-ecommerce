package com.melek.ecommerce.cart.infrastructure.catalog;

import com.melek.ecommerce.cart.application.port.ProductCatalog;
import com.melek.ecommerce.cart.application.port.ProductData;
import com.melek.ecommerce.catalog.product.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;

import java.util.Optional;

public class ProductCatalogAdapter implements ProductCatalog {

    private final ProductRepository productRepository;

    public ProductCatalogAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductData findById(ProductId id) {
        Product product = productRepository.findById(id)
            .orElseThrow(
                () -> new ProductNotFoundException(id)
            );

        return new ProductData(
            product.getId(),
            product.getPrice()
        );
    }
}
