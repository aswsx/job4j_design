CREATE
    OR REPLACE PROCEDURE delete_data()
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE
    FROM products
    WHERE products.id > 5;
END;
$$;