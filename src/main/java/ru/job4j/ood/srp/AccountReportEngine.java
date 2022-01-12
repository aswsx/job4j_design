package ru.job4j.ood.srp;

import java.util.function.Predicate;

/**
 * @author Alex Gutorov
 * @version 1.0
 * <p>
 * Класс генерирует отчеты для бухгалтерии.
 * Выдает список сотрудников в виде текста:
 * Имя; Дата принятия; Дата увольнения; Зарплата
 * По требованию отдела изменен вид зарплаты.
 * Теперь зарплата исчисляется в попугаях.
 * parrotSalaryCoeff коэффициент пересчета зарплаты в попугаи
 */
public class AccountReportEngine implements Report {
    private final Store store;
    double parrotSalaryCoeff;

    public AccountReportEngine(Store store, double parrotSalaryCoeff) {
        this.store = store;
        this.parrotSalaryCoeff = parrotSalaryCoeff;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        var text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        for (Employee e : store.findBy(filter)) {
            double newSalary = salaryChange(e.getSalary());
            text.append(e.getName())
                    .append(";")
                    .append(e.getHired())
                    .append(";")
                    .append(e.getFired())
                    .append(";")
                    .append(newSalary)
                    .append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public double salaryChange(double salary) {
        return salary * parrotSalaryCoeff;
    }
}