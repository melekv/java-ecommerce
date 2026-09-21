package com.melek.ecommerce.order.infrastructure.persistence;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.domain.model.*;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
@Transactional
public class OrderRepositoryIntegrationTest {

    @Container
    private static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18.6");

    @DynamicPropertySource
    private static void configureProperties(
        DynamicPropertyRegistry registry
    ) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void Should_Save_And_Find_Order() {
        OrderId orderId = OrderId.generate();
        OrderItemId orderItemId = OrderItemId.generate();
        ProductId productId = ProductId.generate();

        UUID customerId = UUID.randomUUID();

        OrderItem item = new OrderItem(
            orderItemId,
            productId,
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            2
        );

        Order order = new Order(
            orderId,
            customerId,
            List.of(item)
        );

        Order savedOrder = orderRepository.save(order);

        assertEquals(orderId, savedOrder.getId());
        assertEquals(customerId, savedOrder.getCustomerId());
        assertEquals(OrderStatus.NEW, savedOrder.getStatus());
        assertEquals(1, savedOrder.getItems().size());

        entityManager.flush();
        entityManager.clear();

        Optional<Order> result = orderRepository.findById(orderId);

        assertTrue(result.isPresent());

        Order foundOrder = result.get();
        assertEquals(orderId, foundOrder.getId());
        assertEquals(customerId, foundOrder.getCustomerId());
        assertEquals(OrderStatus.NEW, foundOrder.getStatus());
        assertEquals(1, foundOrder.getItems().size());

        OrderItem foundItem = foundOrder.getItems().get(0);

        assertEquals(orderItemId, foundItem.getId());
        assertEquals(productId, foundItem.getProductId());
        assertEquals("Keyboard", foundItem.getProductName());
        assertEquals(
            0,
            BigDecimal.valueOf(100).compareTo(
                foundItem.getUnitPrice().amount()
            )
        );
        assertEquals(
            Currency.getInstance("PLN"),
            foundItem.getUnitPrice().currency()
        );
        assertEquals(2, foundItem.getQuantity());
    }
}
