package ru.job4j.lsp.parking.model;

/**
 * Класс грузовая машина
 */
public class Truck extends Car {
    /**
     * Размер шрузовой машины
     * Может быть разным, но всегда больше 1
     */
    private final int size;

    /**
     * Конструктор
     *
     * @param size размер грузовой машины
     */
    public Truck(int size) {
        this.size = size;
    }

    /**
     * Геттер
     *
     * @return размер грузовой машины
     */
    @Override
    public int getSize() {
        return size;
    }
}
