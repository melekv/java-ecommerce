package com.melek.ecommerce.inventory.application;

import com.melek.ecommerce.inventory.domain.model.InventoryItem;
import com.melek.ecommerce.inventory.domain.repository.InventoryRepository;

import java.util.UUID;

public class ReserveStockUseCase {

    private final InventoryRepository inventoryRepository;

    public ReserveStockUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void reserve(UUID productId, int quantity) {
        InventoryItem inventoryItem = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new IllegalStateException(
                "Inventory item not found for product: " + productId
            ));

        inventoryItem.reserve(quantity);

        inventoryRepository.save(inventoryItem);
    }
}
