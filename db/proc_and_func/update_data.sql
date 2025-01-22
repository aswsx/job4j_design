CREATE
    OR REPLACE PROCEDURE update_data(u_count integer, tax float, u_id integer)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    IF u_count > 0 THEN
        UPDATE products
        SET count = count - u_count
        WHERE id = u_id;
    END IF;
    IF
        tax > 0 THEN
        UPDATE products
        SET price = price + price * tax;
    END IF;
END;
$$;