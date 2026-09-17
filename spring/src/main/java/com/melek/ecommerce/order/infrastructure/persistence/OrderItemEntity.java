package com.melek.ecommerce.order.infrastructure.persistence;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "unit_price", precision = 19, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private int quantity;

    protected OrderItemEntity() {
    }

    public OrderItemEntity(
        UUID id,
        OrderEntity order,
        UUID productId,
        String productName,
        BigDecimal unitPrice,
        String currency,
        int quantity
    ) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.currency = currency;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public int getQuantity() {
        return quantity;
    }
}
