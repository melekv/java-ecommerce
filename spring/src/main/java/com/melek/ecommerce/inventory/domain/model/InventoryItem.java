package com.melek.ecommerce.inventory.domain.model;

import java.util.UUID;

public class InventoryItem {

    private final UUID productId;

    private int quantity;

    public InventoryItem(UUID productId, int quantity) {
        if (productId == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than or equal to 0");
        }

        this.productId = productId;
        this.quantity = quantity;
    }

    public void reserve(int quantity) {
        if (quantity <= 0 || quantity > this.quantity) {
            throw new IllegalArgumentException("Quantity must be greater than 0 and less than or equal to the available quantity");
        }

        this.quantity -= quantity;
    }

    public void release(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to release must be positive");
        }

        this.quantity += quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
