package com.melek.ecommerce.catalog.product.infrastructure.web;

import com.melek.ecommerce.catalog.product.application.dto.ProductResponse;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper {

    public ProductResponse map(Product product) {
        return new ProductResponse(
            product.getId().value(),
            product.getName(),
            product.getDescription(),
            product.getPrice().amount(),
            product.getPrice().currency().getCurrencyCode(),
            product.getCategoryId().value()
        );
    }
}
