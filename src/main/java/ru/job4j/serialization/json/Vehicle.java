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
        final Vehicle saabVehicle = new Vehicle(true, 3, new Car("SAAB"), "Sedan", "Turbo");

        /* Преобразуем объект Vehicle в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String saabVehicleToJson = gson.toJson(saabVehicle);
        System.out.println(saabVehicleToJson);

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
        final Vehicle saabVehicleFromJson = gson.fromJson(saabVehicleToJson, Vehicle.class);
        System.out.println(vehicleMod);
        System.out.println(saabVehicleFromJson);
    }

}