package com.melek.ecommerce.order.domain.model;

import com.melek.ecommerce.shared.domain.model.Money;

import java.util.List;
import java.util.UUID;

public class Order {

    private final OrderId id;
    private final UUID customerId;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(
        OrderId id,
        UUID customerId,
        List<OrderItem> items
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        this.id = id;
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.status = OrderStatus.NEW;
    }

    public Order(
        UUID customerId,
        List<OrderItem> items
    ) {
        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        this.id = OrderId.generate();
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.status = OrderStatus.NEW;
    }

    public Order(
        OrderId id,
        UUID customerId,
        List<OrderItem> items,
        OrderStatus status
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (customerId == null) {
            throw new IllegalArgumentException("Customer id cannot be null");
        }

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        this.id = id;
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.status = status;
    }

    public OrderId getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Money total() {
        return items
            .stream()
            .map(OrderItem::total)
            .reduce(Money::add)
            .orElseThrow();
    }

    public void confirm() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Only new orders can be confirmed");
        }

        status = OrderStatus.CONFIRMED;
    }

    public void pay() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed orders can be paid");
        }

        status = OrderStatus.PAID;
    }

    public void ship() {
        if (status != OrderStatus.PAID) {
            throw new IllegalStateException("Only paid orders can be shipped");
        }

        status = OrderStatus.SHIPPED;
    }

    public void deliver() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Only shipped orders can be delivered");
        }

        status = OrderStatus.DELIVERED;
    }

    public void cancel() {
        if (status != OrderStatus.NEW && status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order cannot be cancelled in current status");
        }

        status = OrderStatus.CANCELLED;
    }
}
