package com.melek.ecommerce.order.infrastructure.catalog;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.order.application.port.ProductCatalog;
import com.melek.ecommerce.order.application.port.ProductData;

import java.util.Optional;

public class ProductCatalogAdapter implements ProductCatalog {

    private final ProductRepository productRepository;

    public ProductCatalogAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<ProductData> findById(ProductId id) {
        return productRepository.findById(id)
            .map(product -> new ProductData(
                product.getId(),
                product.getName(),
                product.getPrice()
            ));
    }
}
