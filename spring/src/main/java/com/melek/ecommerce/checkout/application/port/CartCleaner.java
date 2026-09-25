package com.melek.ecommerce.checkout.application.port;

import com.melek.ecommerce.customer.domain.model.CustomerId;

public interface CartCleaner {

    void clear(CustomerId customerId);
}
