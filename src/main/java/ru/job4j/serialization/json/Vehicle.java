package ru.job4j.serialization.json;

import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vehicle {
    private final boolean passenger;
    private final int serviceLife;
    private final Car car;
    private final String[] mods;

    public boolean isPassenger() {
        return passenger;
    }

    public int getServiceLife() {
        return serviceLife;
    }

    public Car getCar() {
        return car;
    }

    public String[] getMods() {
        return mods;
    }

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
        final var saabVehicle = new Vehicle(true, 3, new Car("SAAB"),
                "Sedan", "Turbo");

        /* Преобразуем объект Vehicle в json-строку. */
        final var gson = new GsonBuilder().create();
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
        final var vehicleMod = gson.fromJson(carJson, Vehicle.class);
        final var saabVehicleFromJson = gson.fromJson(saabVehicleToJson, Vehicle.class);
        System.out.println(vehicleMod);
        System.out.println(saabVehicleFromJson);

        /* JSONObject из json-строки строки */
        var jsonModel = new JSONObject("{\"model\":\"SAAB\"}");
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Sedan");
        list.add("Turbo");
        var jsonMods = new JSONArray(list);
        /* JSONObject напрямую методом put */
        var jsonObject = new JSONObject();
        jsonObject.put("passenger", saabVehicle.isPassenger());
        jsonObject.put("serviceLife", saabVehicle.getServiceLife());
        jsonObject.put("model", jsonModel);
        jsonObject.put("mods", jsonMods);
        /* Выведем результат в консоль */
        System.out.println(jsonObject);
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(saabVehicle));
    }
}