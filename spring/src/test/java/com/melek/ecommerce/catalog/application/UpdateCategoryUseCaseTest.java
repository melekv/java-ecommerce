package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryUseCaseTest {

    @Mock
    private CategoryRepository repository;

    @Test
    public void Should_Update_Category() {
        CategoryId id = CategoryId.generate();

        UpdateCategoryUseCase useCase = new UpdateCategoryUseCase(repository);

        Category category = new Category(id, "Food");
        Category updatedCategory = new Category(id, "Travel");

        when(repository.findById(id))
            .thenReturn(Optional.of(category));

        when(repository.update(any(Category.class)))
            .thenReturn(updatedCategory);

        Category result = useCase.execute(id, "Travel");

        assertNotNull(result);
        assertEquals("Travel", result.getName());

        ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);

        verify(repository).findById(id);
        verify(repository).update(captor.capture());

        Category savedCategory = captor.getValue();
        assertEquals("Travel", savedCategory.getName());
    }

    @Test
    public void Should_Throw_Category_Not_Found() {
        CategoryId id = CategoryId.generate();

        UpdateCategoryUseCase useCase = new UpdateCategoryUseCase(repository);

        when(repository.findById(id))
            .thenReturn(Optional.empty());

        assertThrows(
            CategoryNotFoundException.class,
            () -> useCase.execute(id, "Travel")
        );
    }
}
