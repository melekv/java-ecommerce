package com.melek.ecommerce.order.infrastructure.persistence;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.model.OrderItem;
import com.melek.ecommerce.order.domain.model.OrderItemId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class OrderPersistenceMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity(
            order.getId().value(),
            order.getCustomerId(),
            order.getStatus()
        );

        entity.setItems(
            order.getItems()
                .stream()
                .map(item -> toEntity(item, entity))
                .toList()
        );

        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        return new Order(
            new OrderId(entity.getId()),
            entity.getCustomerId(),
            entity.getItems()
                .stream()
                .map(this::toDomain)
                .toList(),
            entity.getStatus()
        );
    }

    private OrderItemEntity toEntity(
        OrderItem item,
        OrderEntity order
    ) {
        return new OrderItemEntity(
            item.getId().value(),
            order,
            item.getProductId().value(),
            item.getProductName(),
            item.getUnitPrice().amount(),
            item.getUnitPrice().currency().getCurrencyCode(),
            item.getQuantity()
        );
    }

    private OrderItem toDomain(OrderItemEntity entity) {
        return new OrderItem(
            new OrderItemId(entity.getId()),
            new ProductId(entity.getProductId()),
            entity.getProductName(),
            Money.of(
                entity.getUnitPrice(),
                Currency.getInstance(entity.getCurrency())
            ),
            entity.getQuantity()
        );
    }
}
