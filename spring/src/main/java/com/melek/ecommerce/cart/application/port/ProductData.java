package com.melek.ecommerce.cart.application.port;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;

public record ProductData(
    ProductId id,
    Money price
) {
}
