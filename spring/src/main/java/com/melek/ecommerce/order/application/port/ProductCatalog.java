package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;

import java.util.Optional;

public interface ProductCatalog {

    Optional<ProductData> findById(ProductId id);
}
