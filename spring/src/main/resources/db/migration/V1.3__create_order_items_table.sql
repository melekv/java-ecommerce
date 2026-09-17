CREATE TABLE order_items(
    id UUID NOT NULL PRIMARY KEY,
    order_id UUID NOT NULL REFERENCES orders,
    product_id UUID NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price NUMERIC(19 ,2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    quantity INTEGER NOT NULL
);
