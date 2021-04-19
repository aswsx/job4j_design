package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Alex Gutorov
 * @version 1.0
 */
public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = read.readLine()) != null) {
                String[] list = line.split(" ");
                if (builder.length() == 0 && (list[0].equals("400") || list[0].equals("500"))) {
                    builder.append(list[1]).append(";");
                }
                if (builder.length() != 0 && (list[0].equals("200") || list[0].equals("300"))) {
                    builder.append(list[1]).append(";");
                    write.println(builder);
                    builder = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  public static void main(String[] args) {
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("resources/unavailable.csv"))) {
////            out.println("15:01:30;15:02:32");
////            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    //  }
}
