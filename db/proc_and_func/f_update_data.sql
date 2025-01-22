CREATE
    OR REPLACE FUNCTION f_update_data(u_count integer, tax float, u_id integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS
$$
DECLARE
    result integer;
BEGIN
    IF u_count > 0 THEN
        UPDATE products
        SET count = count - u_count
        WHERE id = u_id;
        SELECT INTO result count
        FROM products
        WHERE id = u_id;
    END IF;
    IF tax > 0 THEN
        UPDATE products
        SET price = price + price * tax;
        SELECT INTO result SUM(price)
        FROM products;
    END IF;
    RETURN result;
END;
$$;