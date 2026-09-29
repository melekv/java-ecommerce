CREATE TABLE payment (
     id UUID PRIMARY KEY,
     order_id UUID NOT NULL UNIQUE,
     customer_id UUID NOT NULL,
     amount NUMERIC(19, 2) NOT NULL,
     currency VARCHAR(3) NOT NULL,
     status VARCHAR(20) NOT NULL
);
