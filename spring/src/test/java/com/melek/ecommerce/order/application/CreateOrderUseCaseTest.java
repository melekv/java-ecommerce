package com.melek.ecommerce.order.application;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.application.dto.OrderItemRequest;
import com.melek.ecommerce.order.application.port.ProductCatalog;
import com.melek.ecommerce.order.application.port.ProductData;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductCatalog productCatalog;

    @Test
    public void Should_Create_Order() {
        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(
            orderRepository,
            productCatalog
        );

        ProductId productId1 = ProductId.generate();
        ProductId productId2 = ProductId.generate();

        List<OrderItemRequest> items = List.of(
            new OrderItemRequest(
                productId1.value(),
                1
            ),
            new OrderItemRequest(
                productId2.value(),
                2
            )
        );

        when(productCatalog.findById(productId1))
            .thenReturn(
                Optional.of(new ProductData(
                    productId1,
                    "Keyboard",
                    Money.of(
                        BigDecimal.valueOf(100),
                        Currency.getInstance("PLN")
                    )
                ))
            );

        when(productCatalog.findById(productId2))
            .thenReturn(
                Optional.of(new ProductData(
                    productId2,
                    "Mouse",
                    Money.of(
                        BigDecimal.valueOf(50),
                        Currency.getInstance("PLN")
                    )
                ))
            );

        when(orderRepository.save(any(Order.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        createOrderUseCase.execute(
            UUID.randomUUID(),
            items
        );

        verify(orderRepository).save(captor.capture());

        Order savedOrder = captor.getValue();

        verify(productCatalog).findById(productId1);
        verify(productCatalog).findById(productId2);

        assertNotNull(savedOrder);
        assertEquals(2, savedOrder.getItems().size());
        assertEquals(productId1, savedOrder.getItems().get(0).getProductId());
        assertEquals("Keyboard", savedOrder.getItems().get(0).getProductName());
        assertEquals(BigDecimal.valueOf(100), savedOrder.getItems().get(0).getUnitPrice().amount());
        assertEquals(1, savedOrder.getItems().get(0).getQuantity());

        assertEquals(productId2, savedOrder.getItems().get(1).getProductId());
        assertEquals("Mouse", savedOrder.getItems().get(1).getProductName());
        assertEquals(BigDecimal.valueOf(50), savedOrder.getItems().get(1).getUnitPrice().amount());
        assertEquals(2, savedOrder.getItems().get(1).getQuantity());
    }
}
