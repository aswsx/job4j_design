CREATE TABLE parts
(
    id         serial PRIMARY KEY,
    name       varchar(255),
    partnumber varchar(255)
);

CREATE TABLE unit
(
    id       serial PRIMARY KEY,
    name     varchar(255),
    parts_id int REFERENCES parts (id)
);