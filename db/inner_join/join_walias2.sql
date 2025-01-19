SELECT p.name, p.partnumber, u.name
FROM parts AS p
         JOIN unit AS u ON p.id = u.parts_id;
