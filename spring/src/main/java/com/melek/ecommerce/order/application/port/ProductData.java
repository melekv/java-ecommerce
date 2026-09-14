package com.melek.ecommerce.order.application.port;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;

public record ProductData(
    ProductId id,
    String name,
    Money price
) {
}
