package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.model.Car;

import java.util.List;

/**
 * Класс реализует интерфейс Parking
 */
public class CarParking implements Parking {
    @Override
    public boolean isEmptyPark(Car car) {
        return false;
    }

    @Override
    public List<Car> getParkedCars() {
        return null;
    }
}

