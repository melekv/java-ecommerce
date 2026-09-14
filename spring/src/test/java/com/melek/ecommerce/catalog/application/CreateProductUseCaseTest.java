package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.category.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.catalog.product.application.CreateProductUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void Should_Create_Product() {

        CreateProductUseCase useCase = new CreateProductUseCase(productRepository, categoryRepository);

        CategoryId categoryId = CategoryId.generate();
        Category category = new Category(categoryId, "Food");

        Product product = new Product(
            ProductId.generate(),
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.of(category));

        when(productRepository.save(any(Product.class)))
            .thenReturn(product);

        Product result = useCase.execute(
            "MacBook Pro",
            "Apple laptop",
            BigDecimal.valueOf(9999),
            Currency.getInstance("PLN"),
            categoryId
        );

        assertNotNull(result);
        assertEquals("MacBook Pro", result.getName());
        assertEquals(
            BigDecimal.valueOf(9999),
            result.getPrice().amount()
        );
        assertEquals(
            categoryId.value(),
            result.getCategoryId().value()
        );

        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void Should_Reject_Product_When_Category_Not_Found() {

        CreateProductUseCase useCase = new CreateProductUseCase(productRepository, categoryRepository);

        CategoryId categoryId = CategoryId.generate();

        when(categoryRepository.findById(categoryId))
            .thenThrow(CategoryNotFoundException.class);

        assertThrows(
            CategoryNotFoundException.class,
            () -> useCase.execute(
                "MacBook Pro",
                "Apple laptop",
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN"),
                categoryId
            )
        );
    }
}
