SELECT type.name, COUNT(product) AS amount
FROM type
         JOIN product ON type.id = product.type_id
GROUP BY type.name
HAVING COUNT(product) < 10;