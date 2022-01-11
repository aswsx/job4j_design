package ru.job4j.lsp.parking.model;

/**
 * Класс легковая машина
 */
public class PassengerCar extends Car {
    /**
     * Размер легковой машины равен 1
     */
    private final int size = 1;

    /**
     * Геттер
     *
     * @return размер машины
     */
    @Override
    public int getSize() {
        return size;
    }
}
