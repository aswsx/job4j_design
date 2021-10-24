package ru.job4j.gc.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс StrongDemo
 *
 * @author Alex Gutorov
 * @version 1.0
 *
 * Класс предназначен для демонстрации работы с сильными ссылками Strong Reference
 * При данном типе ссылок объекты удаляются только если на них нет сильной ссылки
 * или они находятся в составе объекта на который нет ссылки.
 */
public class StrongDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
    }

    /**
     * В первом методе, мы создаем объект и далее их за'null'яем.
     * Вызываем сборщик мусора и ждем некоторое время. Объекты удаляются, т.к. ссылки на них null
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            objects[i] = new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Object removed! example1");
                }
            };
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * Во втором методе, мы создаем объекты вместе с вложенными. Удаляя внешние объекты
     * как в примере выше удаляются и вложенные, не смотря на то что они не null.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        Object[] objects = new Object[100];
        for (int i = 0; i < 100; i++) {
            Object object = new Object() {
                Object innerObject = new Object() {
                    @Override
                    protected void finalize() throws Throwable {
                        System.out.println("Remove inner object! example2");
                    }
                };
            };
            objects[i] = object;
        }
        for (int i = 0; i < 100; i++) {
            objects[i] = null;
        }
        System.gc();
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     *Проблема данного типа ссылок является то, что если в программе есть неиспользуемые
     * ссылки на созданные объекты, то они не будут удалены, что приведет к утечке памяти,
     * что в свою очередь может привести к ошибке OutOfMemoryException - ситуации
     * когда программе не хватает выделенной памяти.
     * Такой код явно приведет к этой ошибке.
     */
    private static void example3() {
        List<String> strings = new ArrayList<>();
        while (true) {
            strings.add(String.valueOf(System.currentTimeMillis()));
        }
    }
}