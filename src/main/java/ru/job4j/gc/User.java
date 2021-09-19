package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class.getName());
    private int age;
    private String name;

    public User() {
    }

    public User(int age) {
        this.age = age;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Перекрытый метод finalize()
     */
    @Override
    protected void finalize() {
        LOG.info(String.format("Removed %d %s%n", age, name));
    }
}
