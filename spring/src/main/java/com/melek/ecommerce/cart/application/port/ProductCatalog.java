package com.melek.ecommerce.cart.application.port;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;

public interface ProductCatalog {

    ProductData findById(ProductId id);
}
