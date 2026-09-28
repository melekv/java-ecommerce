package com.melek.ecommerce.inventory.application;

import com.melek.ecommerce.inventory.domain.model.InventoryItem;
import com.melek.ecommerce.inventory.domain.repository.InventoryRepository;

import java.util.UUID;

public class CreateStockUseCase {

    private final InventoryRepository inventoryRepository;

    public CreateStockUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void create(UUID productId) {
        InventoryItem inventoryItem = new InventoryItem(
            productId,
            0
        );

        inventoryRepository.save(inventoryItem);
    }
}
