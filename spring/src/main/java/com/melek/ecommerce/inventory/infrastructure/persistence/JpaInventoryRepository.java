package com.melek.ecommerce.inventory.infrastructure.persistence;

import com.melek.ecommerce.inventory.domain.model.InventoryItem;
import com.melek.ecommerce.inventory.domain.repository.InventoryRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaInventoryRepository implements InventoryRepository {

    private final InventoryJpaRepository repository;

    public JpaInventoryRepository(InventoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<InventoryItem> findByProductId(UUID productId) {
        return repository.findById(productId)
            .map(entity ->
                    new InventoryItem(
                        entity.getProductId(),
                        entity.getQuantity()
                    )
                );
    }

    @Override
    public void save(InventoryItem inventoryItem) {
        InventoryEntity entity = new InventoryEntity(
            inventoryItem.getProductId(),
            inventoryItem.getQuantity()
        );

        repository.save(entity);
    }
}
