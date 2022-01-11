package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.model.Car;

import java.util.List;

/**
 * Интерфейс парковки
 */
public interface Parking {
    /**
     * Метод, "паркующий" машину
     *
     * @param car передаваемая на вход машина(пассажирская или грузовая)
     * @return при удачной парковке возвращается true
     */
    boolean parkTheCar(Car car);

    /**
     * метод выдает список запаркованных машин
     *
     * @return возвращаемый список
     */
    List<Car> getParkPlace();
}
