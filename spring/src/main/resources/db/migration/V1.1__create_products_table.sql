CREATE TABLE products(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    price NUMERIC(19, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    category_id UUID NOT NULL REFERENCES categories
);
