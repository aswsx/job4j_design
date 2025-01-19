CREATE TABLE departments
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE employees
(
    id            serial PRIMARY KEY,
    name          varchar(255),
    department_id int REFERENCES departments (id)
);

