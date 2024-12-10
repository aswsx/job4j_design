package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {

    public static String multiple(int size) {
        var builder = new StringBuilder();
        for (var i = 0; i < size; i++) {
            for (var j = 0; j < size; j++) {
                builder.append((i + 1) * (j + 1));
                if (j + 1 == size) {
                    builder.append(System.lineSeparator());
                } else {
                    builder.append("  ");
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        try (var out = new FileOutputStream("data/Matrix.txt")) {
            out.write(multiple(9).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
