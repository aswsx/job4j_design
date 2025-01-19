SELECT product.name AS product, type.name AS type_name
FROM product
         JOIN type ON product.type_id = type.id;