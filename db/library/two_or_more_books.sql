SELECT s.name, COUNT(a.name), a.name
FROM students s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
GROUP BY (s.name, a.name)
HAVING COUNT(a.name) >= 2;