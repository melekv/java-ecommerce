package com.melek.ecommerce.cart.domain.repository;

import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.customer.domain.model.CustomerId;

import java.util.Optional;

public interface CartRepository {

    Cart save(Cart cart);

    Optional<Cart> findByCustomerId(CustomerId customerId);

    void delete(Cart cart);
}
