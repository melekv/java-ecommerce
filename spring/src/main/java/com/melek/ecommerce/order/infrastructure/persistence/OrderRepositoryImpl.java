package com.melek.ecommerce.order.infrastructure.persistence;

import com.melek.ecommerce.order.domain.model.Order;
import com.melek.ecommerce.order.domain.model.OrderId;
import com.melek.ecommerce.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository repository;

    private final OrderPersistenceMapper mapper;

    public OrderRepositoryImpl(
        JpaOrderRepository repository,
        OrderPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = repository.save(
            mapper.toEntity(order)
        );

        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Order> findById(OrderId id) {

        return repository.findById(id.value())
            .map(mapper::toDomain);
    }
}
