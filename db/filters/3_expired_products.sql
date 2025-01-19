SELECT *
FROM product
WHERE expired_date < CURRENT_DATE;