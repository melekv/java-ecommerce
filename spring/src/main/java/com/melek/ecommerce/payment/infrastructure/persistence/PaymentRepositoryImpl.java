package com.melek.ecommerce.payment.infrastructure.persistence;

import com.melek.ecommerce.payment.domain.model.Payment;
import com.melek.ecommerce.payment.domain.model.PaymentId;
import com.melek.ecommerce.payment.domain.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository repository;
    private PaymentMapper mapper;

    public PaymentRepositoryImpl(
        JpaPaymentRepository repository,
        PaymentMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Payment> findByOrderId(UUID orderId) {
        return repository.findByOrderId(orderId)
                .map(mapper::toDomain);
    }

    @Override
    public void save(Payment payment) {
        repository.save(mapper.toEntity(payment));
    }
}
