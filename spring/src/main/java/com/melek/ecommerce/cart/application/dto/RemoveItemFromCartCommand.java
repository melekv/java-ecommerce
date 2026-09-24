package com.melek.ecommerce.cart.application.dto;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public record RemoveItemFromCartCommand(
    CustomerId customerId,
    ProductId productId
) {
}
