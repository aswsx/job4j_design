package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Vehicle {
    private final boolean passenger;
    private final int serviceLife;
    private final Car car;
    private final String[] mods;

    public Vehicle(boolean passenger, int serviceLife, Car car, String... mods) {
        this.passenger = passenger;
        this.serviceLife = serviceLife;
        this.car = car;
        this.mods = mods;
    }

    @Override
    public String toString() {
        return "Car{"
                + "passenger=" + passenger
                + ", serviceLife=" + serviceLife
                + ", car=" + car
                + ", mods=" + Arrays.toString(mods)
                + '}';
    }

    public static void main(String[] args) {
        final Vehicle vehicle = new Vehicle(true, 3, new Car("SAAB"), "Sedan", "Turbo");

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(vehicle));

        /* Модифицируем json-строку */
        final String carJson =
                "{"
                        + "\"passenger\":true,"
                        + "\"serviceLife\":13,"
                        + "\"car\":"
                        + "{"
                        + "\"model\":\"Volvo\""
                        + "},"
                        + "\"mods\":"
                        + "[\"Coupe\",\"Atmo\"]"
                        + "}";
        final Vehicle vehicleMod = gson.fromJson(carJson, Vehicle.class);
        System.out.println(vehicleMod);
    }

}