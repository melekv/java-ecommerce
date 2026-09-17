package com.melek.ecommerce.order.infrastructure.persistence;

import com.melek.ecommerce.order.domain.model.OrderStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItemEntity> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected OrderEntity() {
    }

    public OrderEntity(
        UUID id,
        UUID customerId,
        List<OrderItemEntity> items,
        OrderStatus status
    ) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.status = status;
    }

    public OrderEntity(
        UUID id,
        UUID customerId,
        OrderStatus status
    ) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }
}
