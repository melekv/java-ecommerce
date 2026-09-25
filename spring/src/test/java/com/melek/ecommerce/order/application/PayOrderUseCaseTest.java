package com.melek.ecommerce.order.application;

import com.melek.ecommerce.catalog.product.domain.model.ProductId;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.order.application.port.PaymentGateway;
import com.melek.ecommerce.order.application.port.PaymentResult;
import com.melek.ecommerce.order.application.port.PaymentStatus;
import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderItem;
import com.melek.ecommerce.order.domain.model.OrderStatus;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import com.melek.ecommerce.shared.domain.model.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PayOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private PayOrderUseCase payOrderUseCase;

    @Test
    public void Should_Pay_Order() {
        OrderItem item = new OrderItem(
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            1
        );

        Order order = new Order(
            CustomerId.generate().value(),
            List.of(item)
        );

        order.confirm();

        when(orderRepository.findById(order.getId()))
            .thenReturn(Optional.of(order));

        when(
            paymentGateway.pay(
                order,
                order.getId().value().toString()
            )
        ).thenReturn(
            new PaymentResult(
                PaymentStatus.SUCCESS,
                "transaction-123"
            )
        );

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        payOrderUseCase.execute(order.getId());

        verify(orderRepository).save(orderArgumentCaptor.capture());

        Order capturedOrder = orderArgumentCaptor.getValue();

        assertSame(OrderStatus.PAID, capturedOrder.getStatus());

        verify(paymentGateway).pay(
            order,
            order.getId().value().toString()
        );
    }

    @Test
    public void Should_Not_Pay_Already_Paid_Order() {
        OrderItem item = new OrderItem(
            ProductId.generate(),
            "Keyboard",
            Money.of(
                BigDecimal.valueOf(100),
                Currency.getInstance("PLN")
            ),
            1
        );

        Order order = new Order(
            CustomerId.generate().value(),
            List.of(item)
        );

        order.confirm();
        order.pay();

        when(orderRepository.findById(order.getId()))
            .thenReturn(Optional.of(order));

        payOrderUseCase.execute(order.getId());

        verify(paymentGateway, never()).pay(
            order,
            order.getId().value().toString()
        );

        verify(orderRepository, never()).save(order);
    }
}
