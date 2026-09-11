package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.application.exception.CategoryNotFoundException;
import com.melek.ecommerce.catalog.application.exception.ProductNotFoundException;
import com.melek.ecommerce.catalog.domain.model.Category;
import com.melek.ecommerce.catalog.domain.model.CategoryId;
import com.melek.ecommerce.catalog.domain.model.Money;
import com.melek.ecommerce.catalog.domain.model.Product;
import com.melek.ecommerce.catalog.domain.model.ProductId;
import com.melek.ecommerce.catalog.domain.repository.CategoryRepository;
import com.melek.ecommerce.catalog.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void Should_Update_Product() {
        ProductId productId = ProductId.generate();
        CategoryId categoryId = CategoryId.generate();

        Category category = new Category(
            categoryId,
            "Food"
        );

        Product product = new Product(
            productId,
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(productRepository.findById(productId))
            .thenReturn(Optional.of(product));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.of(category));

        when(productRepository.save(any(Product.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        UpdateProductUseCase useCase = new UpdateProductUseCase(
            productRepository,
            categoryRepository
        );

        Product result = useCase.execute(
            productId,
            "iWatch",
            "Apple watch",
            BigDecimal.valueOf(2999),
            Currency.getInstance("PLN"),
            categoryId
        );

        assertNotNull(result);

        assertEquals(productId.value(), result.getId().value());
        assertEquals("iWatch", result.getName());
        assertEquals("Apple watch", result.getDescription());
        assertEquals(
            BigDecimal.valueOf(2999),
            result.getPrice().amount()
        );
        assertEquals(
            Currency.getInstance("PLN"),
            result.getPrice().currency()
        );
        assertEquals(
            categoryId.value(),
            result.getCategoryId().value()
        );

        ArgumentCaptor<Product> captor =
            ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(captor.capture());

        Product savedProduct = captor.getValue();

        assertEquals(
            productId.value(),
            savedProduct.getId().value()
        );
        assertEquals(
            "iWatch",
            savedProduct.getName()
        );
        assertEquals(
            "Apple watch",
            savedProduct.getDescription()
        );
        assertEquals(
            BigDecimal.valueOf(2999),
            savedProduct.getPrice().amount()
        );
        assertEquals(
            Currency.getInstance("PLN"),
            savedProduct.getPrice().currency()
        );
        assertEquals(
            categoryId.value(),
            savedProduct.getCategoryId().value()
        );
    }

    @Test
    void Should_Throw_When_Product_Does_Not_Exist() {
        ProductId productId = ProductId.generate();
        CategoryId categoryId = CategoryId.generate();

        when(productRepository.findById(productId))
            .thenReturn(Optional.empty());

        UpdateProductUseCase useCase = new UpdateProductUseCase(
            productRepository,
            categoryRepository
        );

        assertThrows(
            ProductNotFoundException.class,
            () -> useCase.execute(
                productId,
                "iWatch",
                "Apple watch",
                BigDecimal.valueOf(2999),
                Currency.getInstance("PLN"),
                categoryId
            )
        );

        verify(productRepository).findById(productId);
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void Should_Throw_When_Category_Does_Not_Exist() {
        ProductId productId = ProductId.generate();
        CategoryId categoryId = CategoryId.generate();

        Product product = new Product(
            productId,
            "MacBook Pro",
            "Apple laptop",
            Money.of(
                BigDecimal.valueOf(9999),
                Currency.getInstance("PLN")
            ),
            categoryId
        );

        when(productRepository.findById(productId))
            .thenReturn(Optional.of(product));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.empty());

        UpdateProductUseCase useCase = new UpdateProductUseCase(
            productRepository,
            categoryRepository
        );

        assertThrows(
            CategoryNotFoundException.class,
            () -> useCase.execute(
                productId,
                "iWatch",
                "Apple watch",
                BigDecimal.valueOf(2999),
                Currency.getInstance("PLN"),
                categoryId
            )
        );

        verify(productRepository, never()).save(any(Product.class));
    }
}
