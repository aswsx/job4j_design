package ru.job4j.ood.srp;

import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 *
 * Класс генерирует отчеты на основе принятого фильтра
 * Выдает список сотрудников в виде текста
 * Имя; Дата принятия; Дата увольнения; Зарплата
 */
public class ReportEngine implements Report {
    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        store.findBy(filter).forEach(e ->
                text.append(e.getName())
                        .append(";")
                        .append(e.getHired())
                        .append(";")
                        .append(e.getFired())
                        .append(";")
                        .append(e.getSalary())
                        .append(";")
                        .append(System.lineSeparator()));
        return text.toString();
    }
}