package ru.job4j.collection.hash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final String name;
    private final int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;

    }

    public static void main(String[] args) {

        User first = new User("Alex",
                2,
                new GregorianCalendar(1985, Calendar.MARCH, 25));
        User second = new User("Alex",
                2,
                new GregorianCalendar(1985, Calendar.MARCH, 25));

        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());

        for (User el : map.keySet()) {
            System.out.println(el + "," + map.get(el));
        }
    }
}
