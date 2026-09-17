package com.melek.ecommerce.order.infrastructure.catalog;

import com.melek.ecommerce.catalog.category.domain.model.CategoryId;
import com.melek.ecommerce.catalog.product.domain.model.Product;
import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.catalog.product.domain.repository.ProductRepository;
import com.melek.ecommerce.order.application.port.ProductData;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductCatalogAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductCatalogAdapter productCatalogAdapter;

    @Test
    public void Should_Return_Product_Data() throws Exception {
        ProductId productId = ProductId.generate();
        CategoryId categoryId = CategoryId.generate();

        when(productRepository.findById(productId))
            .thenReturn(Optional.of(new Product(
                productId,
                "Keyboard",
                "Fancy keyboard",
                Money.of(
                    BigDecimal.valueOf(100),
                    Currency.getInstance("PLN")
                ),
                categoryId
            )));

        ProductData product = productCatalogAdapter.findById(productId)
                .orElseThrow();

        verify(productRepository).findById(productId);

        assertNotNull(product);
        assertEquals(productId, product.id());
        assertEquals("Keyboard", product.name());
        assertEquals(BigDecimal.valueOf(100), product.price().amount());
    }

    @Test
    public void Should_Return_Empty_When_Product_Not_Found() {
        ProductId productId = ProductId.generate();

        when(productRepository.findById(productId))
            .thenReturn(Optional.empty());

        ProductData product = productCatalogAdapter.findById(productId)
                .orElse(null);

        verify(productRepository).findById(productId);

        assertNull(product);
    }
}
