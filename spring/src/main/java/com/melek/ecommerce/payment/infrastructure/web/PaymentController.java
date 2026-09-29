package com.melek.ecommerce.payment.infrastructure.web;

import com.melek.ecommerce.payment.application.ProcessPaymentUseCase;
import com.melek.ecommerce.payment.domain.model.Payment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping("/{orderId}")
    public Payment process(@PathVariable UUID orderId) {
        return processPaymentUseCase.execute(orderId);
    }
}
