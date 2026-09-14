package com.melek.ecommerce.order.application.dto;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;

public record OrderItemRequest(
    ProductId id,
    int quantity
) {
}
