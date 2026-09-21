package com.melek.ecommerce.order.application;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.order.application.exception.OrderNotFoundException;
import com.melek.ecommerce.order.domain.model.*;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfirmOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ConfirmOrderUseCase useCase;

    @Test
    public void Should_Confirm_Order() {
        OrderItem orderItem = new OrderItem(
            OrderItemId.generate(),
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            2
        );

        Order order = new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            List.of(orderItem)
        );

        when(orderRepository.findById(order.getId()))
            .thenReturn(Optional.of(order));

        when(orderRepository.save(any(Order.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);

        useCase.execute(order.getId());

        verify(orderRepository).save(captor.capture());

        Order savedOrder = captor.getValue();

        assertEquals(OrderStatus.CONFIRMED, savedOrder.getStatus());
    }

    @Test
    public void Should_Throw_When_Order_Not_Found() {
        OrderId orderId = OrderId.generate();

        when(orderRepository.findById(orderId))
            .thenReturn(Optional.empty());

        assertThrows(
            OrderNotFoundException.class,
            () -> useCase.execute(orderId)
        );

        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void Should_Throw_When_Status_Is_Confirmed() {
        OrderItem orderItem = new OrderItem(
            OrderItemId.generate(),
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            2
        );

        Order order = new Order(
            OrderId.generate(),
            UUID.randomUUID(),
            List.of(orderItem),
            OrderStatus.CONFIRMED
        );

        when(orderRepository.findById(order.getId()))
            .thenReturn(Optional.of(order));

        assertThrows(
            IllegalStateException.class,
            () -> useCase.execute(order.getId())
        );

        verify(orderRepository, never()).save(any(Order.class));
    }
}
