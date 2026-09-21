package com.melek.ecommerce.order.infrastructure.web;

import com.melek.ecommerce.order.application.dto.OrderItemResponse;
import com.melek.ecommerce.order.application.dto.OrderResponse;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper {

    public OrderResponse map(Order order) {
        return new OrderResponse(
            order.getId().value(),
            order.getCustomerId(),
            order.getStatus(),
            order.getItems().stream()
                .map(this::map)
                .toList()
        );
    }

    private OrderItemResponse map(OrderItem orderItem) {
        return new OrderItemResponse(
            orderItem.getId().value(),
            orderItem.getProductId().value(),
            orderItem.getProductName(),
            orderItem.getUnitPrice().amount(),
            orderItem.getUnitPrice().currency().getCurrencyCode(),
            orderItem.getQuantity()
        );
    }
}
