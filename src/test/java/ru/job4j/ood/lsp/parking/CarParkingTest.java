package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.parking.model.PassengerCar;
import ru.job4j.ood.lsp.parking.model.Truck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarParkingTest {
    PassengerCar passCar1;
    PassengerCar passCar2;
    PassengerCar passCar3;
    PassengerCar passCar4;
    Truck truck1;
    Truck truck2;
    Truck truck3;
    Truck truck4;

    @Before
    public void init() {
        this.passCar1 = new PassengerCar();
        this.passCar2 = new PassengerCar();
        this.passCar3 = new PassengerCar();
        this.passCar4 = new PassengerCar();
        this.truck1 = new Truck(2);
        this.truck2 = new Truck(2);
        this.truck3 = new Truck(3);
        this.truck4 = new Truck(2);
    }

    /**
     * В тестах последняя строка будет изменена на assertFalse после реализации логики.
     * assertTrue прописано ддля возможности сборки проекта
     */
    @Test
    public void whenPark2PassengerCarsAnd2TruckThenNoMoreParking() {
        var parking = new CarParking(2, 2);
        assertTrue(parking.parkTheCar(passCar1));
        assertTrue(parking.parkTheCar(passCar2));
        assertTrue(parking.parkTheCar(truck1));
        assertTrue(parking.parkTheCar(truck2));
        assertFalse(parking.parkTheCar(passCar3));
    }

    @Test
    public void whenParkingOnlyFor1PassengerCarsThenTruckImpossibleToPark() {
        var parking = new CarParking(1, 0);
        assertTrue(parking.parkTheCar(passCar1));
        assertFalse(parking.parkTheCar(truck1));
    }

    @Test
    public void when2PassengerCarPlacesEmptyThenCanParkOnly1TruckWithSize2() {
        var parking = new CarParking(2, 0);
        assertTrue(parking.parkTheCar(truck1));
        assertFalse(parking.parkTheCar(passCar1));
    }

    @Test
    public void when2PassengerCarPlacesEmptyThenTruckWithSizeMore2ImpossibleToPark() {
        var parking = new CarParking(2, 0);
        assertFalse(parking.parkTheCar(truck3));
    }

    @Test
    public void whenOnlyTruckPlacesThenPassengerCarImpossibleToPark() {
        var parking = new CarParking(0, 2);
        assertFalse(parking.parkTheCar(passCar1));
    }
}