package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.model.Car;

import java.util.List;

/**
 * Класс реализует интерфейс Parking
 */
public class CarParking implements Parking {
    /**
     * Парковочные места
     */
    private final List<Car> parkPlace;
    /**
     * Легковая машина
     */
    private final int passCar;
    /**
     * Грузовая машина
     */
    private final int truck;

    /**
     * Конструктор
     *
     * @param passCar   легковая машина
     * @param truck     грузовая машина
     * @param parkPlace парковочное место
     */
    public CarParking(int passCar, int truck, List<Car> parkPlace) {
        this.passCar = passCar;
        this.truck = truck;
        this.parkPlace = parkPlace;
    }

    /**
     * Метод, "паркующий" машины
     *
     * @param car передаваемая на вход машина(пассажирская или грузовая)
     * @return true, если парковка удалась
     */
    @Override
    public boolean parkTheCar(Car car) {
        return true;
    }

    /**
     * Метод, возвращающий список припаркованных машин
     *
     * @return список машин
     */
    @Override
    public List<Car> getParkedCars() {
        return null;
    }
}