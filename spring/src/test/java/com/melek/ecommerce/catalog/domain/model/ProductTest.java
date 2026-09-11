package com.melek.ecommerce.catalog.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    private final Currency PLN = Currency.getInstance("PLN");

    @Test
    void Should_Not_Allow_Empty_Name() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Product(
                ProductId.generate(),
                null,
                "Description",
                new Money(
                    BigDecimal.valueOf(100),
                    PLN
                ),
                CategoryId.generate()
            )
        );
    }
}
