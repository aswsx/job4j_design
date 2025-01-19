CREATE TABLE universities
(
    id   serial PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE students
(
    id            serial PRIMARY KEY,
    name          varchar(255),
    course        int,
    budget        bool,
    speciality    varchar(255),
    enroll_date   timestamp,
    university_id int REFERENCES universities (id)
);

INSERT INTO universities(name)
VALUES ('U1');
INSERT INTO universities(name)
VALUES ('U2');
INSERT INTO universities(name)
VALUES ('U3');
INSERT INTO universities(name)
VALUES ('U4');
INSERT INTO universities(name)
VALUES ('U5');

INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('AB', 1, TRUE, 'S1', date '2020-09-01', 1);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('AC', 2, TRUE, 'S1', date '2019-09-02', 1);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('AD', 3, TRUE, 'M1', date '2018-09-03', 1);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('AE', 4, FALSE, 'Z1', date '2017-09-04', 1);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('AF', 5, FALSE, 'E1', date '2016-09-05', 1);

INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('BC', 1, TRUE, 'Q2', date '2020-09-01', 2);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('BD', 2, TRUE, 'R2', date '2019-09-02', 2);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('BE', 3, FALSE, 'T2', date '2018-09-03', 2);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('BF', 4, FALSE, 'Y2', date '2017-09-04', 2);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('BG', 5, FALSE, 'U2', date '2016-09-05', 2);

INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('CD', 1, TRUE, 'A3', date '2020-09-01', 3);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('CE', 2, TRUE, 'D3', date '2019-09-01', 3);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('CF', 3, FALSE, 'F3', date '2018-09-01', 3);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('CG', 4, FALSE, 'G3', date '2017-09-01', 3);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('CH', 5, TRUE, 'H3', date '2016-09-01', 3);

INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('DE', 1, FALSE, 'Z4', date '2020-09-01', 4);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('DF', 2, TRUE, 'X4', date '2019-09-01', 4);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('DG', 3, TRUE, 'C4', date '2018-09-01', 4);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('DH', 4, FALSE, 'V4', date '2017-09-01', 4);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('DJ', 5, TRUE, 'V4', date '2016-09-01', 4);

INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('EF', 1, TRUE, 'P5', date '2020-09-01', 5);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('EG', 2, TRUE, 'O5', date '2019-09-01', 5);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('EH', 3, FALSE, 'I5', date '2018-09-01', 5);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('EJ', 4, TRUE, 'J5', date '2017-09-01', 5);
INSERT INTO students(name, course, budget, speciality, enroll_date, university_id)
VALUES ('EI', 5, TRUE, 'K5', date '2016-09-01', 5);