package com.melek.ecommerce.catalog.application;

import com.melek.ecommerce.catalog.category.domain.model.Category;
import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.category.domain.repository.CategoryRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.catalog.product.application.UpdateProductUseCase;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void Should_Update_Product() {
        ProductId productId = ProductId.generate();

        UpdateProductUseCase useCase = new UpdateProductUseCase(productRepository, categoryRepository);

        CategoryId categoryId = CategoryId.generate();
        Category category = new Category(categoryId, "Food");

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

        when(productRepository.findById(product.getId()))
            .thenReturn(Optional.of(product));

        when(categoryRepository.findById(categoryId))
            .thenReturn(Optional.of(category));

        useCase.execute(
            product.getId(),
            "iWatch",
            "Apple watch",
            BigDecimal.valueOf(2999),
            Currency.getInstance("PLN"),
            categoryId
        );

        ArgumentCaptor<Product> captor =
            ArgumentCaptor.forClass(Product.class);

        verify(productRepository).update(captor.capture());

        Product savedProduct = captor.getValue();

        assertEquals(productId.value(), savedProduct.getId().value());
        assertEquals("iWatch", savedProduct.getName());
        assertEquals("Apple watch", savedProduct.getDescription());
        assertEquals(
            BigDecimal.valueOf(2999),
            savedProduct.getPrice().amount()
        );
        assertEquals(
            categoryId.value(),
            savedProduct.getCategoryId().value()
        );
    }
}
