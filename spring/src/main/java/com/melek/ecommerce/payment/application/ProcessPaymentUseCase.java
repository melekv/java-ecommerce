package com.melek.ecommerce.payment.application;

import com.melek.ecommerce.payment.application.port.PaymentEventPublisher;
import com.melek.ecommerce.payment.domain.model.Payment;
import com.melek.ecommerce.payment.domain.repository.PaymentRepository;
import com.melek.ecommerce.shared.messaging.event.PaymentSucceededEvent;

import java.util.UUID;

public class ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    public ProcessPaymentUseCase(
        PaymentRepository paymentRepository,
        PaymentEventPublisher paymentEventPublisher
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentEventPublisher = paymentEventPublisher;
    }

    public Payment execute(UUID orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
            .orElseThrow(
                () -> new IllegalStateException("Payment not found for order: " + orderId)
            );

        payment.markAsPaid();

        paymentRepository.save(payment);

        paymentEventPublisher.publishSucceeded(
            new PaymentSucceededEvent(
                payment.getId().value(),
                payment.getOrderId()
            )
        );

        return payment;
    }
}
