package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
    private static final Logger LOG = LoggerFactory.getLogger(Person.class.getName());
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
       LOG.info(String.format("Removed %d %s%n", age, name));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}