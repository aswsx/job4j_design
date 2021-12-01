package ru.job4j.cache;

import java.util.Scanner;

/**
 * Класс эмулятор работы с кешем. Позволяет пользователю загрузить содержимое файла в кеш,
 * вывести содержимое кеша на экран, удалить содержимое файла из кеша
 */
public class Emulator extends DirFileCache {
    public Emulator(String cachingDir) {
        super(cachingDir);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator("job4j_design/src/main/java/ru/job4j/cache/");
        Scanner sc = new Scanner(System.in);
        System.out.println("Укажите имя файла:");
        String sb = emulator.get(sc.nextLine());
        System.out.println("Содержимое файла: " + sb);
    }
}
