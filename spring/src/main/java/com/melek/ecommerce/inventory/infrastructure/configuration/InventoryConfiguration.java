package com.melek.ecommerce.inventory.infrastructure.configuration;

import com.melek.ecommerce.inventory.application.CreateStockUseCase;
import com.melek.ecommerce.inventory.application.ReserveStockUseCase;
import com.melek.ecommerce.inventory.domain.repository.InventoryRepository;
import com.melek.ecommerce.order.application.port.OrderEventPublisher;
import com.melek.ecommerce.order.infrastructure.messaging.RabbitMqOrderEventPublisher;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfiguration {

    @Bean
    public OrderEventPublisher orderEventPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMqOrderEventPublisher(rabbitTemplate);
    }

    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
        ConnectionFactory connectionFactory,
        JacksonJsonMessageConverter messageConverter
    ) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    @Bean
    public ReserveStockUseCase reserveStockUseCase(InventoryRepository inventoryRepository) {
        return new ReserveStockUseCase(inventoryRepository);
    }

    @Bean
    public CreateStockUseCase createStockUseCase(InventoryRepository inventoryRepository) {
        return new CreateStockUseCase(inventoryRepository);
    }
}
