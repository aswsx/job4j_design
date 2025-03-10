-- Создать таблицу company
DROP TABLE IF EXISTS company CASCADE;
CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

-- Создать таблицу person
DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer REFERENCES company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

-- заполнить таблицу company
INSERT INTO company(id, name)
VALUES (1, 'Рога и Копыта');
INSERT INTO company(id, name)
VALUES (2, 'Облпотребсоюз');
INSERT INTO company(id, name)
VALUES (3, 'Консервный завод №1');
INSERT INTO company(id, name)
VALUES (4, 'Три кита');
INSERT INTO company(id, name)
VALUES (5, 'Автопарк №2');
INSERT INTO company(id, name)
VALUES (6, 'ДоДо Пицца');

-- заполнить таблицу person
INSERT INTO person(id, name, company_id)
VALUES (1, 'Alibaba', 6);
INSERT INTO person(id, name, company_id)
VALUES (2, 'Abdul', 6);
INSERT INTO person(id, name, company_id)
VALUES (3, 'Ivan', 1);
INSERT INTO person(id, name, company_id)
VALUES (4, 'Fedor', 2);
INSERT INTO person(id, name, company_id)
VALUES (5, 'Nikolay', 2);
INSERT INTO person(id, name, company_id)
VALUES (6, 'Mack', 3);
INSERT INTO person(id, name, company_id)
VALUES (7, 'John', 3);
INSERT INTO person(id, name, company_id)
VALUES (8, 'Nigel', 3);
INSERT INTO person(id, name, company_id)
VALUES (9, 'James', 3);
INSERT INTO person(id, name, company_id)
VALUES (10, 'Jury', 5);
INSERT INTO person(id, name, company_id)
VALUES (11, 'Sergey', 6);
INSERT INTO person(id, name, company_id)
VALUES (12, 'Andrey', 5);
INSERT INTO person(id, name, company_id)
VALUES (13, 'Michael', 4);
INSERT INTO person(id, name, company_id)
VALUES (14, 'Hercule', 4);

-- получить имена всех person, которые не состоят в компании с id = 5;
-- название компании для каждого человека.

SELECT p.name person, c.name company
FROM person p
         JOIN company c ON p.company_id = c.id
WHERE c.id != 5;

--выбрать название компании с максимальным количеством человек + количество человек в этой компании.

SELECT c.name company, COUNT(p.name) person_qty
FROM company c
         JOIN person p ON c.id = p.company_id
GROUP BY c.name
ORDER BY person_qty DESC
LIMIT 1;
