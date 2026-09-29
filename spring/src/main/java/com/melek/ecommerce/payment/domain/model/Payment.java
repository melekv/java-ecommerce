package com.melek.ecommerce.payment.domain.model;

import com.melek.ecommerce.shared.domain.model.Money;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    private final PaymentId id;

    private final UUID orderId;

    private final UUID customerId;

    private final Money amount;

    private PaymentStatus status;

    public Payment(
        PaymentId id,
        UUID orderId,
        UUID customerId,
        Money amount,
        PaymentStatus status
    ) {
        if (id == null || orderId == null || customerId == null || amount == null) {
            throw new IllegalArgumentException("Payment id, order id, customer id and amount cannot be null");
        }

        if (amount.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount cannot be negative or zero");
        }

        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
    }

    public Payment(
        UUID orderId,
        UUID customerId,
        Money amount
    ) {
        this(
            PaymentId.generate(),
            orderId,
            customerId,
            amount,
            PaymentStatus.PENDING
        );
    }

    public void markAsPaid() {
        if (status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Only pending payments can be marked as paid");
        }

        status = PaymentStatus.PAID;
    }

    public void markAsFailed() {
        if (status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Only pending payments can be marked as failed");
        }

        status = PaymentStatus.FAILED;
    }

    public PaymentId getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Money getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
