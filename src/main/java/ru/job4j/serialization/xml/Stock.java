package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Stock {

    private final boolean availability;
    private final int quantity;
    private final Detail detail;
    private final String[] statuses;

    public Stock(boolean availability, int quantity, Detail detail, String... statuses) {
        this.availability = availability;
        this.quantity = quantity;
        this.detail = detail;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Stock{"
                + "availability=" + availability
                + ", quantity=" + quantity
                + ", detail=" + detail
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Stock stock = new Stock(true, 25, new Detail("Part", "01210203"), "reserved", "limited");
    }


}