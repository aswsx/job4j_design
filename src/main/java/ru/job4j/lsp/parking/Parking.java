package ru.job4j.lsp.parking;

import ru.job4j.lsp.parking.model.Car;

import java.util.List;

/**
 * Интерфейс парковки
 */
public interface Parking {
    /**
     * Метод, проверяющий наличие пустых мест на парковке
     * @param car передаваемый на вход автомобиль (пассажирский или грузовой)
     * @return при наличии подходящего места возвращается true
     */
    boolean isEmptyPark(Car car);

    /**
     * метод выдает список запаркованных автомобилей
     * @return возвращаемый список
     */
    List<Car> getParkedCars();
}
