SELECT *
FROM employees e
         LEFT JOIN departments d ON e.department_id = d.id;

SELECT *
FROM departments d
         RIGHT JOIN employee e ON e.id = d.department_id;