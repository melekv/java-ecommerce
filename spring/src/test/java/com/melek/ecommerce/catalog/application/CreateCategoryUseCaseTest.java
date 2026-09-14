package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.category.application.CreateCategoryUseCase;
import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @Mock
    private CategoryRepository repository;

    @Test
    void Should_Create_Category() {

        CreateCategoryUseCase useCase = new CreateCategoryUseCase(repository);

        Category category = new Category(
            CategoryId.generate(),
            "Food"
        );

        when(repository.save(any(Category.class)))
            .thenReturn(category);

        Category result = useCase.execute(
            "Food"
        );

        assertNotNull(result);
        assertEquals("Food", result.getName());

        verify(repository).save(any(Category.class));
    }
}
