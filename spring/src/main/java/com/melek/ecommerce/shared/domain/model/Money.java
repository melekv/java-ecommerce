package com.melek.ecommerce.shared.domain.model;

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

    public Money multiply(int multiplier) {
        return new Money(
            amount.multiply(
                BigDecimal.valueOf(multiplier)
            ),
            currency
        );
    }

    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must match");
        }

        return new Money(
            amount.add(other.amount),
            currency
        );
    }
}
