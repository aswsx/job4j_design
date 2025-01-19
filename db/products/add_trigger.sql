CREATE TRIGGER discount_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE discount();

CREATE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING new TABLE AS
        inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax();

CREATE TRIGGER history_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE insert_into_history_table();