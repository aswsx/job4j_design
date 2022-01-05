package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 *
 * Класс генерирует отчеты для отдела HR
 * Выдает отчет в виде текста
 * Имя; Зарплата
 * При этом выполняется сортировка по убыванию оклада
 */
public class HRReportEngine implements Report, Comparator<Employee> {
    private final Comparator<Employee> comp;
    private final Store store;

    public HRReportEngine(Store store, Comparator<Employee> comp) {
        this.comp = comp;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var text = new StringBuilder();
        var list = store.findBy(filter);
        list.sort(comp);
        text.append("Name; Salary").append(System.lineSeparator());
        list.forEach(e -> text
                .append(e.getName())
                .append(";")
                .append(e.getSalary())
                .append(";")
                .append(System.lineSeparator()));
        return text.toString();
    }

    @Override
    public int compare(Employee e1, Employee e2) {
        return Double.compare(e1.getSalary(), e2.getSalary());
    }
}
