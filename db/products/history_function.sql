CREATE OR REPLACE FUNCTION insert_into_history_table()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name, NEW.price, CURRENT_DATE);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;