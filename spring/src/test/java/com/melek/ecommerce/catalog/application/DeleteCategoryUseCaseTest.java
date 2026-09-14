package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryUseCaseTest {

    @Mock
    private CategoryRepository repository;

    @Test
    public void Should_Delete_Category() {
        CategoryId id = CategoryId.generate();

        DeleteCategoryUseCase useCase = new DeleteCategoryUseCase(repository);

        Category category = new Category(id, "Food");

        when(repository.findById(id))
            .thenReturn(Optional.of(category));

        useCase.execute(id);

        verify(repository).findById(id);
        verify(repository).delete(id);
    }

    @Test
    public void Should_Throw_Category_Not_Found() {
        CategoryId id = CategoryId.generate();

        DeleteCategoryUseCase useCase = new DeleteCategoryUseCase(repository);

        when(repository.findById(id))
            .thenReturn(Optional.empty());

        assertThrows(
            CategoryNotFoundException.class,
            () -> useCase.execute(id)
        );
    }
}
