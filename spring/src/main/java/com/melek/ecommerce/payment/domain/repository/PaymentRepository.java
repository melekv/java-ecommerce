package com.melek.ecommerce.payment.domain.repository;

import com.melek.ecommerce.payment.domain.model.Payment;
import com.melek.ecommerce.payment.domain.model.PaymentId;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Optional<Payment> findById(PaymentId id);

    Optional<Payment> findByOrderId(UUID orderId);

    void save(Payment payment);
}
