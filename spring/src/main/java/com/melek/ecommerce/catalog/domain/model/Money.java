package com.melek.ecommerce.catalog.domain.model;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(
    BigDecimal amount,
    Currency currency
) {
    public Money {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be empty");
        }
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }
}
