SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product);


