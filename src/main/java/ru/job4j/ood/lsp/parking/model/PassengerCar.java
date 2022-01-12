package ru.job4j.ood.lsp.parking.model;

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
     */
    public PassengerCar() {
        this.size = 1;
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
