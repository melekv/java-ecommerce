package com.melek.ecommerce.catalog.infrastructure.web;

import com.melek.ecommerce.catalog.application.dto.CategoryResponse;
import com.melek.ecommerce.catalog.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper {

    public CategoryResponse map(Category category) {
        return new CategoryResponse(
            category.getId().value(),
            category.getName()
        );
    }
}
