package com.melek.ecommerce.inventory.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, UUID> {
}
