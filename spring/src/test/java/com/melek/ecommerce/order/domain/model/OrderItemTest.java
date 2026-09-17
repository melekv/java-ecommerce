package com.melek.ecommerce.order.domain.model;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderItemTest {

    @Test
    public void Should_Throw_When_Quantity_Is_Zero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new OrderItem(
                OrderItemId.generate(),
                ProductId.generate(),
                "Yamaha motocycle",
                Money.of(
                    BigDecimal.valueOf(1000),
                    Currency.getInstance("PLN")
                ),
                0
            )
        );
    }

    @Test
    public void Should_Throw_When_Quantity_Is_Negative() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new OrderItem(
                OrderItemId.generate(),
                ProductId.generate(),
                "Yamaha motocycle",
                Money.of(
                    BigDecimal.valueOf(1000),
                    Currency.getInstance("PLN")
                ),
                -10
            )
        );
    }

    @Test
    public void Should_Count_Total() {
        OrderItem orderItem = new OrderItem(
            OrderItemId.generate(),
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            10
        );

        assertEquals(
            BigDecimal.valueOf(1000),
            orderItem.total().amount()
        );
    }
}
