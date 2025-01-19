SELECT *
FROM employees e
         LEFT JOIN departments d ON e.department_id = d.id;

SELECT *
FROM employees e
         LEFT JOIN departments d ON e.dep_id = d.id;

SELECT *
FROM employees e
         RIGHT JOIN departments d ON e.department_id = d.id;

SELECT *
FROM employees e
         FULL JOIN departments d ON e.department_id = d.id;

SELECT *
FROM employees
         CROSS JOIN departments;