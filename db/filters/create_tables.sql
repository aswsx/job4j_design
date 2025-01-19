CREATE TABLE type
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE product
(
    id           serial PRIMARY KEY,
    name         varchar(255),
    type_id      int REFERENCES type (id),
    expired_date date,
    price        float
);

