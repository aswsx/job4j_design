CREATE
    OR REPLACE FUNCTION discount()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price - price * 0.2
    WHERE count <= 5
      AND id = new.id;
    RETURN NEW;
END;
$$
    LANGUAGE 'plpgsql';