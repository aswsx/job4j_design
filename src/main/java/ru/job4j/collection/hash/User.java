package ru.job4j.collection.hash;

import java.util.*;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

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
        var user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        var a = Integer.hashCode(children);
        int b = birthday.hashCode();
        result = 31 * result + a;
        result = 31 * result + b;
        return result;
    }

    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1985,
                Calendar.MARCH,
                25,
                18,
                0,
                0);
        birthday.set(Calendar.MILLISECOND, 0);
        var first = new User("Alex",
                2, birthday);
        var second = new User("Alex",
                2, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());

        for (User el : map.keySet()) {
            System.out.println(el + "," + map.get(el));
        }
    }
}
