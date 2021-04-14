package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] str = text.toString().split(System.lineSeparator());
            for (String s : str) {
                System.out.println(s + " " + (Integer.parseInt(s) % 2 == 0 ? "Четное" : "Нечетное"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
