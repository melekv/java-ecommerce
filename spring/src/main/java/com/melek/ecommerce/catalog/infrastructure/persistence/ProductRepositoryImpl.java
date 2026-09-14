package com.melek.ecommerce.catalog.infrastructure.persistence;

import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository repository;
    private final ProductPersistenceMapper mapper;

    public ProductRepositoryImpl(
        JpaProductRepository repository,
        ProductPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = mapper.toEntity(product);

        ProductEntity savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return repository.findById(id.value())
            .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = mapper.toEntity(product);

        ProductEntity savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(ProductId id) {
        repository.deleteById(id.value());
    }
}
