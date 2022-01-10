package ru.job4j.lsp.parking.model;

/**
 * Класс легковой автомобиль
 */
public class PassengerCar extends Car {
    private final int size = 1;

    @Override
    public int getSize() {
        return size;
    }
}
