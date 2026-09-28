package com.melek.ecommerce.inventory.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    private UUID productId;

    @Column(nullable = false)
    private int quantity;

    public InventoryEntity() {
    }

    public InventoryEntity(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
