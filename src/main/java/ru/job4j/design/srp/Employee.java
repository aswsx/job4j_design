package ru.job4j.design.srp;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author Alex Gutorov
 * @version 1.0
 * <p>
 * Класс описывает модель данных
 * Содержит геттеры, сеттеры, конструктор
 * Переопределены equals и hashCode
 */
public class Employee {
    private String name;
    private final Calendar hired;
    private final Calendar fired;
    private final double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }

    public double setSalary(double salary) {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}