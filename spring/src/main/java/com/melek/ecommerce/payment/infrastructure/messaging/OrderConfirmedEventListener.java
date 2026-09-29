package com.melek.ecommerce.payment.infrastructure.messaging;

import com.melek.ecommerce.payment.application.CreatePaymentUseCase;
import com.melek.ecommerce.shared.domain.model.Money;
import com.melek.ecommerce.shared.messaging.event.OrderConfirmedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class OrderConfirmedEventListener {

    private final CreatePaymentUseCase createPaymentUseCase;

    public OrderConfirmedEventListener(CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @RabbitListener(queues = "payment.order-confirmed")
    public void handle(OrderConfirmedEvent event) {
        createPaymentUseCase.execute(
            event.orderId(),
            event.customerId(),
            Money.of(
                event.amount(),
                Currency.getInstance(event.currency())
            )
        );
    }
}
