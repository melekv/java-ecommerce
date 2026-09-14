package com.melek.ecommerce.catalog.domain.model;

import com.melek.ecommerce.catalog.product.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    private final Currency PLN = Currency.getInstance("PLN");

    @Test
    void Should_Create_Money() {
        Money money = new Money(
            BigDecimal.valueOf(100),
            PLN
        );

        assertEquals(
            BigDecimal.valueOf(100),
            money.amount()
        );

        assertEquals(
            PLN,
            money.currency()
        );
    }

    @Test
    void Should_Not_Allow_Null_Amount() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Money(null, PLN)
        );
    }

    @Test
    void Should_Not_Allow_Null_Currency() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new Money(BigDecimal.valueOf(100), null)
        );
    }
}
