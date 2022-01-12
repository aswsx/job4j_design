package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует интерфейс Parking
 */
public class CarParking implements Parking {
    /**
     * количество парковок для легковой машины
     */
    private int passCarPlaces;
    /**
     * Количество парковок для грузовой машины
     */
    private int truckPlaces;
    /**
     * Парковочные места
     */
    private final List<Car> parkPlace = new ArrayList<>();

    /**
     * Конструктор
     *
     * @param passCarPlaces легковая машина
     * @param truckPlaces   грузовая машина
     */
    public CarParking(int passCarPlaces, int truckPlaces) {
        this.passCarPlaces = passCarPlaces;
        this.truckPlaces = truckPlaces;
    }

    /**
     * Метод, возвращающий необходимые парковочные места
     *
     * @return список необходимого количества парковочных мест
     */
    @Override
    public List<Car> getParkPlace() {
        var list = parkPlace;
        return list;
    }

    /**
     * Метод, "паркующий" машины
     *
     * @param car передаваемая на вход машина(пассажирская или грузовая)
     * @return true, если парковка удалась
     */
    @Override
    public boolean parkTheCar(Car car) {
        if (car.getSize() == 1 && passCarPlaces > 0) {
            getParkPlace().add(car);
            passCarPlaces--;
            return true;
        }
        if (car.getSize() > 1) {
            if (truckPlaces > 0) {
                getParkPlace().add(car);
                truckPlaces--;
                return true;
            } else if (car.getSize() <= passCarPlaces) {
                getParkPlace().add(car);
                passCarPlaces -= car.getSize();
                return true;
            }
        }
        return false;
    }
}
