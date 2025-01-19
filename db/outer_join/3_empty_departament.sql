SELECT departments.*
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;

