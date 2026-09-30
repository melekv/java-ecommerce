package com.melek.ecommerce.catalog.product.infrastructure.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.UUID;

@Document(indexName = "products")
public class ProductDocument {

    @Id
    private UUID id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Keyword)
    private String currency;

    @Field(type = FieldType.Keyword)
    private UUID categoryId;

    public ProductDocument() {
    }

    public ProductDocument(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        UUID categoryId
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public UUID getCategoryId() {
        return categoryId;
    }
}
