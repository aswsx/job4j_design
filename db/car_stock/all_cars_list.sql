SELECT car.name AS car, body.name AS body, engine.name AS engine, transmission.name AS transmission
FROM car
         LEFT JOIN body ON car.body_id = body.id
         LEFT JOIN engine ON car.engine_id = engine.id
         LEFT JOIN transmission ON car.transmission_id = transmission.id;