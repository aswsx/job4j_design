CREATE TABLE role
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE rules
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE users
(
    id      serial PRIMARY KEY,
    name    varchar(255),
    role_id int REFERENCES role (id)
);

CREATE TABLE rolerules
(
    id       serial PRIMARY KEY,
    role_id  int REFERENCES role (id),
    rules_id int REFERENCES rules (id)
);

CREATE TABLE category
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE state
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE item
(
    id          serial PRIMARY KEY,
    name        varchar(255),
    users_id    int REFERENCES users (id),
    category_id int REFERENCES category (id),
    state_id    int REFERENCES state (id)
);

CREATE TABLE comments
(
    id      serial PRIMARY KEY,
    comment text,
    item_id int REFERENCES item (id)
);

CREATE TABLE attachs
(
    id      serial PRIMARY KEY,
    name    varchar(255),
    item_id int REFERENCES item (id)
);



