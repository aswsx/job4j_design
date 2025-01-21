CREATE OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price * 1.16
    WHERE id = (SELECT id FROM inserted);
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';
