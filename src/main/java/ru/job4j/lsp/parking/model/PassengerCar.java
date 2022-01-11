package ru.job4j.lsp.parking.model;

/**
 * Класс легковая машина
 */
public class PassengerCar extends Car {
    /**
     * Размер легковой машины
     */
    private final int size;

    /**
     * Конструктор
     *
     * @param size размер машины
     */
    public PassengerCar(int size) {
        this.size = size;
    }

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
