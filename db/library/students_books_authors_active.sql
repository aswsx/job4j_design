SELECT s.name   student_name,
       b.name   book_name,
       a.name   author_name,
       o.active order_status
FROM orders o
         JOIN
     students s ON o.student_id = s.id
         JOIN
     books b ON o.book_id = b.id
         JOIN
     authors a ON b.author_id = a.id
WHERE o.active = TRUE
ORDER BY s.name, b.name;