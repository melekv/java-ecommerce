package com.melek.ecommerce.payment.infrastructure.persistence;

import com.melek.ecommerce.payment.domain.model.Payment;
import com.melek.ecommerce.payment.domain.model.PaymentId;
import com.melek.ecommerce.shared.domain.model.Money;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class PaymentMapper {

    public PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
            payment.getId().value(),
            payment.getOrderId(),
            payment.getCustomerId(),
            payment.getAmount().amount(),
            payment.getAmount().currency().getCurrencyCode(),
            payment.getStatus()
        );
    }

    public Payment toDomain(PaymentEntity paymentEntity) {
        return new Payment(
            new PaymentId(paymentEntity.getId()),
            paymentEntity.getOrderId(),
            paymentEntity.getCustomerId(),
            Money.of(
                paymentEntity.getAmount(),
                Currency.getInstance(paymentEntity.getCurrency())
            ),
            paymentEntity.getStatus()
        );
    }
}
