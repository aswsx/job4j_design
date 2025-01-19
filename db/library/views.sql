CREATE VIEW show_students_with_2_or_more_books
AS
SELECT s.name AS student, COUNT(a.name), a.name AS author
FROM students AS s
         JOIN orders o ON s.id = o.student_id
         JOIN books b ON o.book_id = b.id
         JOIN authors a ON b.author_id = a.id
GROUP BY (s.name, a.name)
HAVING COUNT(a.name) >= 2;

SELECT *
FROM show_students_with_2_or_more_books;

CREATE VIEW show_detail_active_orders
AS
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

SELECT *
FROM show_detail_active_orders;

CREATE VIEW show_quantity_by_author_desc
AS
SELECT a.name                            author_name,
       COUNT(b.id)                       total_books_ordered,
       STRING_AGG(DISTINCT s.name, ', ') students_ordered_books
FROM authors a
         JOIN
     books b ON a.id = b.author_id
         JOIN
     orders o ON b.id = o.book_id
         JOIN
     students s ON o.student_id = s.id
WHERE o.active = TRUE
GROUP BY a.id
ORDER BY total_books_ordered DESC;

SELECT *
FROM show_quantity_by_author_desc;