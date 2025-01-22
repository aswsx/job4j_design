CREATE
    OR REPLACE FUNCTION f_delete_data(u_count integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
AS
$$
DECLARE
    result integer;
BEGIN
    DELETE
    FROM products
    WHERE products.count > u_count;
    RETURN result;
END;
$$;

SELECT f_delete_data(5)