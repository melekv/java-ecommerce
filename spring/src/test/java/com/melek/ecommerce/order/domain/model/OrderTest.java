package com.melek.ecommerce.order.domain.model;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    public void Should_Calculate_Total() {
        List<OrderItem> items = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem(
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            10
        );

        items.add(orderItem1);

        OrderItem orderItem2 = new OrderItem(
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(10),
                Currency.getInstance("PLN")
            ),
            2
        );

        items.add(orderItem2);

        Order order = new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            items
        );

        assertEquals(
            BigDecimal.valueOf(1020),
            order.total().amount()
        );
    }

    @Test
    public void Should_Create_Order_With_New_status() {
        Order order = createOrder();

        assertEquals(OrderStatus.NEW, order.getStatus());
    }

    @Test
    public void Should_Confirm_New_Order() {
        Order order = createOrder();

        order.confirm();

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    public void Should_Cancel_New_Order() {
        Order order = createOrder();

        order.cancel();

        assertEquals(OrderStatus.CANCELLED, order.getStatus());
    }

    @Test
    public void Should_Process_Order_Through_Complete_Lifecycle() {
        Order order = createOrder();

        order.confirm();
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());

        order.pay();
        assertEquals(OrderStatus.PAID, order.getStatus());

        order.ship();
        assertEquals(OrderStatus.SHIPPED, order.getStatus());

        order.deliver();
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    private Order createOrder() {
        OrderItem orderItem = new OrderItem(
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            10
        );

        return new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            List.of(orderItem)
        );
    }
}
