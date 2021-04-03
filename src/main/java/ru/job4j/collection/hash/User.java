package ru.job4j.collection.hash;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1985,
                Calendar.MARCH,
                25,
                18,
                0,
                0);
        birthday.set(Calendar.MILLISECOND, 0);
        User first = new User("Alex",
                2, birthday);
        User second = new User("Alex",
                2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());

        for (User el : map.keySet()) {
            System.out.println(el + "," + map.get(el));
        }
    }
}
