CREATE OR REPLACE FUNCTION discount()
    RETURNS trigger AS
$$
BEGIN
    NEW.price := NEW.price * 1.2;
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';

ALTER FUNCTION discount() OWNER TO postgres;