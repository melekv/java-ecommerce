package com.melek.ecommerce.catalog.category.domain.model;

public class Category {

    private final CategoryId id;
    private String name;

    public Category(
        CategoryId id,
        String name
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Category id cannot be null");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        this.id = id;
        this.name = name;
    }

    public void changeName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
