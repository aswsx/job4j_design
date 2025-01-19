INSERT INTO type(name)
VALUES ('сыр');
INSERT INTO type(name)
VALUES ('хлеб');
INSERT INTO type(name)
VALUES ('молоко');
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('мааздам', '2021.11.25', 1300, 1);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('плавленый', '2021.05.12', 750, 1);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('голландский', '2022.01.01', 1850, 1);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('бородинский', '2021.07.23', 350, 2);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('отрубной', '2021.07.18', 280, 2);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('шадринское', '2021.08.15', 350, 3);
INSERT INTO product(name, expired_date, price, type_id)
VALUES ('отборное', '2021.10.12', 450, 3);