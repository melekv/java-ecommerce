package com.melek.ecommerce.payment.application;

import com.melek.ecommerce.payment.domain.model.Payment;
import com.melek.ecommerce.payment.domain.repository.PaymentRepository;
import com.melek.ecommerce.shared.domain.model.Money;

import java.util.UUID;

public class CreatePaymentUseCase {

    private final PaymentRepository paymentRepository;

    public CreatePaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment execute(
        UUID orderId,
        UUID customerId,
        Money amount
    ) {
        if (paymentRepository.findByOrderId(orderId).isPresent()) {
            throw new IllegalStateException("Payment already exists for order with id: " + orderId);
        }

        Payment payment = new Payment(
            orderId,
            customerId,
            amount
        );

        paymentRepository.save(payment);

        return payment;
    }
}
