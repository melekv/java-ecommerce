package com.melek.ecommerce.payment.infrastructure.configuration;

import com.melek.ecommerce.payment.application.CreatePaymentUseCase;
import com.melek.ecommerce.payment.application.ProcessPaymentUseCase;
import com.melek.ecommerce.payment.application.port.PaymentEventPublisher;
import com.melek.ecommerce.payment.domain.repository.PaymentRepository;
import com.melek.ecommerce.payment.infrastructure.messaging.RabbitMqPaymentEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(
        PaymentRepository repository
    ) {
        return new CreatePaymentUseCase(repository);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(
        PaymentRepository repository,
        PaymentEventPublisher paymentEventPublisher
    ) {
        return new ProcessPaymentUseCase(repository, paymentEventPublisher);
    }

    @Bean
    public PaymentEventPublisher paymentEventPublisher(
        RabbitTemplate rabbitTemplate
    ) {
        return new RabbitMqPaymentEventPublisher(rabbitTemplate);
    }
}
