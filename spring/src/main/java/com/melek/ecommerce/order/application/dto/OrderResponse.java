package com.melek.ecommerce.order.application.dto;

import com.melek.ecommerce.order.domain.model.OrderStatus;

import java.util.List;
import java.util.UUID;

public record OrderResponse(
    UUID id,
    UUID customerId,
    OrderStatus status,
    List<OrderItemResponse> items
) {
}
