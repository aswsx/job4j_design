package ru.job4j.ood.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 * Класс генерирует отчет в формате JSON
 */
public class JSONGenerator implements Report {

    private final Store store;

    public JSONGenerator(Store store) {
        this.store = store;
    }

    /**
     * Метод генерирует отчет в формате JSON
     * @param filter фильтр для выбора элементов из списка
     * @return возвращаемый отчет JSON
     * bcghfdkty
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder().create().toJson(store.findBy(filter));
    }
}
