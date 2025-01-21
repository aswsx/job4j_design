DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    id       serial PRIMARY KEY,
    name     varchar(50),
    producer varchar(50),
    count    integer DEFAULT 0,
    price    integer
);
