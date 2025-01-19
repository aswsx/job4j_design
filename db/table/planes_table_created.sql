CREATE TABLE planes
(
    id               serial PRIMARY KEY,
    model            varchar(255),
    engines_qty      INT,
    engines_type     text,
    passengers_plane boolean,
    passengers_qty   INT
);