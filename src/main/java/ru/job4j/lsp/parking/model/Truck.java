package ru.job4j.lsp.parking.model;
/**
 * Класс грузовой автомобиль
 */
public class Truck extends Car {
    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
