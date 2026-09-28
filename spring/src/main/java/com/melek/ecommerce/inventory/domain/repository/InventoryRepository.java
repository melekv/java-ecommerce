package com.melek.ecommerce.inventory.domain.repository;

import com.melek.ecommerce.inventory.domain.model.InventoryItem;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository {

    Optional<InventoryItem> findByProductId(UUID productId);

    void save(InventoryItem inventoryItem);
}
