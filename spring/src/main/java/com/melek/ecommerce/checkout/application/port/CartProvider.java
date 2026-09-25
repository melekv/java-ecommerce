package com.melek.ecommerce.checkout.application.port;

import com.melek.ecommerce.cart.domain.model.Cart;
import com.melek.ecommerce.customer.domain.model.CustomerId;

public interface CartProvider {

    Cart findByCustomerId(CustomerId customerId);
}
