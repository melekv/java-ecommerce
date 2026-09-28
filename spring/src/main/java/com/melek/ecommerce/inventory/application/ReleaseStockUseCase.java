package com.melek.ecommerce.inventory.application;

import com.melek.ecommerce.inventory.domain.model.InventoryItem;
import com.melek.ecommerce.inventory.domain.repository.InventoryRepository;

import java.util.UUID;

public class ReleaseStockUseCase {

    private final InventoryRepository inventoryRepository;

    public ReleaseStockUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void release(UUID productId, int quantity) {
        InventoryItem inventoryItem = inventoryRepository.findByProductId(productId)
            .orElseThrow(
                () -> new IllegalStateException("Inventory item not found for product id: " + productId)
            );

        inventoryItem.release(quantity);

        inventoryRepository.save(inventoryItem);
    }
}
