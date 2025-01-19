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