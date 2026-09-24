package com.melek.ecommerce.cart.infrastructure.persistence;

import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.cart.domain.repository.CartRepository;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import org.springframework.data.redis.core.StringRedisTemplate;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

public class RedisCartRepository implements CartRepository {

    private final StringRedisTemplate redisTemplate;

    private final CartRedisMapper mapper;

    private final ObjectMapper objectMapper;

    public RedisCartRepository(
        StringRedisTemplate redisTemplate,
        CartRedisMapper mapper,
        ObjectMapper objectMapper
    ) {
        this.redisTemplate = redisTemplate;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public Cart save(Cart cart) {
        CartRedisData data = mapper.toData(cart);

        String json;
        try {
            json = objectMapper.writeValueAsString(data);
        } catch (JacksonException e) {
            throw new IllegalStateException("Could not serialize cart", e);
        }

        redisTemplate.opsForValue().set(
            key(cart.getCustomerId()),
            json
        );

        return cart;
    }

    @Override
    public Optional<Cart> findByCustomerId(CustomerId customerId) {
        String json = redisTemplate
            .opsForValue()
            .get(key(customerId));

        if (json == null) {
            return Optional.empty();
        }

        CartRedisData data;
        try {
            data = objectMapper.readValue(
                json,
                CartRedisData.class
            );
        } catch (JacksonException e) {
            throw new IllegalStateException("Could not deserialize cart", e);
        }

        return Optional.of(
            mapper.toDomain(data)
        );
    }

    private String key(CustomerId customerId) {
        return "cart:" + customerId.value();
    }
}
