package ru.job4j.ood.srp;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}