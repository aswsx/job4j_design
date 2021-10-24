package ru.job4j.gc;

public class MyGCDemo {

    public static void main(String[] args) {
        GCDemo.info();
        for (int i = 0; i < 5000; i++) {
            new User(i);
        }
        GCDemo.info();
    }
}
