package com.melek.ecommerce.catalog.domain.repository;

import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(CategoryId id);

    List<Category> findAll();

    Category update(Category category);

    void delete(Category category);
}
