package com.melek.ecommerce.catalog.product.infrastructure.persistence;

import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ProductPersistenceMapper {

    public ProductEntity toEntity(Product product) {
        return new ProductEntity(
            product.getId().value(),
            product.getName(),
            product.getDescription(),
            product.getPrice().amount(),
            product.getPrice().currency().getCurrencyCode(),
            product.getCategoryId().value()
        );
    }

    public Product toDomain(ProductEntity entity) {
        return new Product(
            new ProductId(entity.getId()),
            entity.getName(),
            entity.getDescription(),
            Money.of(
                entity.getPrice(),
                Currency.getInstance(entity.getCurrency())
            ),
            new CategoryId(entity.getCategoryId())
        );
    }
}
