CREATE TABLE body
(
    id   serial PRIMARY KEY,
    name varchar(255)
);
CREATE TABLE engine
(
    id   serial PRIMARY KEY,
    name varchar(255)
);
CREATE TABLE transmission
(
    id   serial PRIMARY KEY,
    name varchar(255)
);
CREATE TABLE car
(
    id              serial PRIMARY KEY,
    name            varchar(255),
    body_id         int REFERENCES body (id),
    engine_id       int REFERENCES engine (id),
    transmission_id int REFERENCES transmission (id)
);