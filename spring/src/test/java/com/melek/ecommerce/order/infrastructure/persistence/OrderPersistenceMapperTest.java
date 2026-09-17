package com.melek.ecommerce.order.infrastructure.persistence;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.model.OrderItem;
import com.melek.ecommerce.order.domain.model.OrderItemId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderPersistenceMapperTest {

    private final OrderPersistenceMapper mapper = new OrderPersistenceMapper();

    @Test
    public void Should_Return_An_Entity() {
        OrderId orderId = OrderId.generate();
        OrderItemId orderItemId = OrderItemId.generate();
        ProductId productId = ProductId.generate();
        UUID customerId = UUID.randomUUID();

        OrderItem orderItem = new OrderItem(
            orderItemId,
            productId,
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            1
        );

        List<OrderItem> items = List.of(orderItem);

        Order order = new Order(
            orderId,
            customerId,
            items
        );

        OrderEntity entity = mapper.toEntity(order);

        assertEquals(orderId.value(), entity.getId());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(order.getStatus(), entity.getStatus());
        assertEquals(order.getItems().size(), entity.getItems().size());

        assertEquals(orderItem.getId().value(), entity.getItems().get(0).getId());
        assertEquals(orderItem.getProductId().value(), entity.getItems().get(0).getProductId());
        assertEquals(orderItem.getProductName(), entity.getItems().get(0).getProductName());
        assertEquals(orderItem.getUnitPrice().amount(), entity.getItems().get(0).getUnitPrice());
        assertEquals(orderItem.getUnitPrice().currency().getCurrencyCode(), entity.getItems().get(0).getCurrency());
        assertEquals(orderItem.getQuantity(), entity.getItems().get(0).getQuantity());
    }

    @Test
    public void Should_Return_A_Domain() {

    }
}
