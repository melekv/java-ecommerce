package com.melek.ecommerce.catalog.domain.model;

public class Product {

    private final ProductId id;
    private String name;
    private String description;
    private Money price;
    private CategoryId categoryId;

    public Product(
        ProductId id,
        String name,
        String description,
        Money price,
        CategoryId categoryId
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }

        if (categoryId == null) {
            throw new IllegalArgumentException("Product categoryId cannot be null");
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public void changeName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void changePrice(Money price) {
        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }

        this.price = price;
    }

    public void changeCategory(CategoryId categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Product categoryId cannot be null");
        }

        this.categoryId = categoryId;
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }
}
