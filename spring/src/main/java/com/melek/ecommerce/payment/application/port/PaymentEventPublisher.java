package com.melek.ecommerce.payment.application.port;

import com.melek.ecommerce.shared.messaging.event.PaymentSucceededEvent;

public interface PaymentEventPublisher {

    void publishSucceeded(PaymentSucceededEvent event);
}
