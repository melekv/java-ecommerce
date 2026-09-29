package com.melek.ecommerce.order.infrastructure.messaging;

import com.melek.ecommerce.order.application.MarkOrderAsPaidUseCase;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.shared.messaging.event.PaymentSucceededEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentSucceededEventListener {

    private final MarkOrderAsPaidUseCase markOrderAsPaidUseCase;

    public PaymentSucceededEventListener(MarkOrderAsPaidUseCase markOrderAsPaidUseCase) {
        this.markOrderAsPaidUseCase = markOrderAsPaidUseCase;
    }

    @RabbitListener(queues = "order.payment-succeeded")
    public void handle(PaymentSucceededEvent event) {
        markOrderAsPaidUseCase.execute(
            new OrderId(event.orderId())
        );
    }
}
