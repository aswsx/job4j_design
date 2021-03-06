package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleChat.class.getName());
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean isPause;

    /**
     * Конструктор
     *
     * @param path       путь к файлу-логу чата
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
    public void run() throws NoSuchAlgorithmException {
        List<String> log = new ArrayList<>();
        List<String> answers = getAnswer();
        Random rnd = SecureRandom.getInstanceStrong();
        try (var reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            String line = reader.readLine();
            log.add(line);
            while (!line.equals(OUT)) {
                if (!checkPause(line)) {
                    String botAnswer = answers.get(rnd.nextInt(answers.size()));
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                }
                line = reader.readLine();
                log.add(line);
            }
        } catch (IOException e) {
            LOG.error("readLineError", e);
        }
        saveLogFile(log);
    }

    /**
     * Метод проверяет входящую строку на команду приостановить ответы от бота
     *
     * @param line принимает на вход строку соббщение пользователя
     * @return возвращает статус бота- приостановлены ответы или нет
     */
    private boolean checkPause(String line) {
        switch (line) {
            case (STOP):
                isPause = true;
                break;
            case (CONTINUE):
                isPause = false;
                break;
            default:
        }
        return isPause;
    }

    /**
     * Метод построчно считывает ответы из текстового файла и записывает их в массив
     *
     * @return возвращает массив с ответами, полученными построчным чтением файла с ответами
     */
    private List<String> getAnswer() {
        List<String> answers = new ArrayList<>();
        try (var reader = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            answers = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error("getAnswerError", e);
        }
        if (answers.isEmpty()) {
            throw new IllegalArgumentException("file botAnswers.txt is empty");
        }
        return answers;
    }

    /**
     * Метод записывает строки из массива лога в файл построчно
     *
     * @param lines принимает массив со строками лога
     */
    private void saveLogFile(List<String> lines) {
        try (var writer = new BufferedWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String msg : lines) {
                writer.write(msg);
                writer.newLine();
            }
        } catch (IOException e) {
            LOG.error("saveLogError", e);
        }
    }

    public static void main(String[] args) {
        try {
            var cc = new ConsoleChat("chat.log", "botAnswers.txt");
            cc.run();
        } catch (Exception e) {
            LOG.error("ChatBotStartException", e);
        }
    }
}