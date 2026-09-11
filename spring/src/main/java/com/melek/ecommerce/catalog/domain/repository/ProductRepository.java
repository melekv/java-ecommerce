package com.melek.ecommerce.catalog.domain.repository;

import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(ProductId id);

    List<Product> findAll();

    void delete(Product product);
}
