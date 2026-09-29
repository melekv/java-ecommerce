package com.melek.ecommerce.payment.infrastructure.messaging;

import com.melek.ecommerce.payment.application.port.PaymentEventPublisher;
import com.melek.ecommerce.shared.messaging.event.PaymentSucceededEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqPaymentEventPublisher implements PaymentEventPublisher {

    public final RabbitTemplate rabbitTemplate;

    public RabbitMqPaymentEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishSucceeded(PaymentSucceededEvent event) {

        rabbitTemplate.convertAndSend(
            "payment.exchange",
            "payment.succeeded",
            event
        );
    }
}
