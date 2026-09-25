package com.melek.ecommerce.order.application.exception;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException() {
        super("Payment failed");
    }
}
