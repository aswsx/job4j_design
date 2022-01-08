package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 *
 * Класс генерирует отчет для отдела программистов
 * Выдает результат в виде HTML
 */
public class ITReportEngine implements Report {
    private final Store store;

    public ITReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var text = new StringBuilder();
        text.append("<DOCTYPE html").append(System.lineSeparator());
        text.append("<html lang =\"en\">").append(System.lineSeparator());
        text.append("<body>").append(System.lineSeparator());
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
        text.append("</body>").append(System.lineSeparator());
        text.append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}