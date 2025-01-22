CREATE
    OR REPLACE PROCEDURE insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    INSERT INTO products (name, producer, count, price)
    VALUES (i_name, prod, i_count, i_price);
END
$$;