package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Класс - реализация простого чат-бота
 *
 * @author Alex Gutorov
 * @version 1.0
 * path путь к файлу-логу чата
 * botAnswers путь к файлу с ответами бота
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean isPause;

    /**
     * Конструктор
     * @param path путь к файлу-логу чата
     * @param botAnswers путь к файлу с ответами бота
     */
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Основной метод бота
     * log - массив, в который пишутся строки лога
     * answers - массив, который содержит ответы из файла
     */
    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = getAnswer();
        Random rnd = new Random();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line = reader.readLine();
            log.add(line);
            while (!line.equals(OUT)) {
                switch (line) {
                    case (STOP) -> {
                        isPause = true;
                    }
                    case (CONTINUE) -> {
                        isPause = false;
                    }
                    default -> {
                    }
                }
                if (!isPause) {
                    String botAnswer = answers.get(rnd.nextInt(answers.size() - 1));
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                }
                line = reader.readLine();
                log.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLogFile(log);
    }

    /**
     * Метод построчно считывает ответы из текстового файла и записывает их в массив
     * @return возвращает массив с ответами, полученными построчным чтением файла с ответами
     */
    private List<String> getAnswer() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            answers = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (answers.size() == 0) {
            throw new IllegalArgumentException("file botAnswers.txt is empty");
        }
        return answers;
    }

    /**
     * Метод записывает строки из массива лога в файл построчно
     * @param lines принимает массив со строками лога
     */
    private void saveLogFile(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String msg : lines) {
                writer.write(msg);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("chat.log", "botAnswers.txt");
        cc.run();
    }
}