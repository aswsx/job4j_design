SELECT body.name
FROM body
         LEFT JOIN car ON body.id = car.body_id
WHERE car.id IS NULL
UNION
SELECT engine.name
FROM engine
         LEFT JOIN car ON engine.id = car.body_id
WHERE car.id IS NULL
UNION
SELECT transmission.name
FROM transmission
         LEFT JOIN car ON transmission.id = car.body_id
WHERE car.id IS NULL;