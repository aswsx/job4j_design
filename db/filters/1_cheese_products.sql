SELECT product.name
FROM product
         JOIN type ON product.type_id = type.id
WHERE type.name = 'сыр';
