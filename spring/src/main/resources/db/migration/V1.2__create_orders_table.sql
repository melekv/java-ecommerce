CREATE TABLE orders(
    id UUID NOT NULL PRIMARY KEY,
    customer_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL
);
