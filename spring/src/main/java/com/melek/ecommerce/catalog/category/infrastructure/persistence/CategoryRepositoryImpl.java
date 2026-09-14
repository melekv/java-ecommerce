package com.melek.ecommerce.catalog.category.infrastructure.persistence;

import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository repository;
    private final CategoryPersistenceMapper mapper;

    public CategoryRepositoryImpl(
        JpaCategoryRepository repository,
        CategoryPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = mapper.toEntity(category);

        CategoryEntity savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(CategoryId id) {
        return repository.findById(id.value())
            .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Category update(Category category) {
        CategoryEntity entity = mapper.toEntity(category);

        CategoryEntity savedEntity = repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public void delete(CategoryId id) {
        repository.deleteById(id.value());
    }
}
