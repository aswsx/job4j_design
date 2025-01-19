INSERT INTO body(name)
VALUES ('sedan'),
       ('cabrio'),
       ('coupe');
INSERT INTO engine(name)
VALUES ('gasoline'),
       ('diesel'),
       ('electric');
INSERT INTO transmission(name)
VALUES ('mechanic'),
       ('auto'),
       ('cvt');
INSERT INTO car(name, body_id, engine_id, transmission_id)
VALUES ('car_1', 1, 1, 1),
       ('car_2', 2, 2, 2);