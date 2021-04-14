package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {

    public static String multiple(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        try (FileOutputStream out = new FileOutputStream("Matrix.txt")) {
            out.write(multiple(9).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}