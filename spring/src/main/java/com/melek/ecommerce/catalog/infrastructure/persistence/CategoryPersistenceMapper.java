package com.melek.ecommerce.catalog.infrastructure.persistence;

import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import org.springframework.stereotype.Component;

@Component
public class CategoryPersistenceMapper {

    public CategoryEntity toEntity(Category category) {
        return new CategoryEntity(
            category.getId().value(),
            category.getName()
        );
    }

    public Category toDomain(CategoryEntity entity) {
        return new Category(
            new CategoryId(entity.getId()),
            entity.getName()
        );
    }
}
