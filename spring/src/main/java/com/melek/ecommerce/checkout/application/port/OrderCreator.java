package com.melek.ecommerce.checkout.application.port;

import com.melek.ecommerce.cart.domain.model.CartItem;
import com.melek.ecommerce.customer.domain.model.CustomerId;
import com.melek.ecommerce.order.domain.model.Order;

import java.util.List;

public interface OrderCreator {

    Order create(CustomerId customerId, List<CartItem> items);
}
