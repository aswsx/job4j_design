package ru.job4j.io.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static ru.exxo.jutil.Printer.println;

/**
 * Класс выполняет запись в файл или вывод в консоль данные из исходного файла
 * в соответствии с заданным фильтром
 *
 * @author Alex Gutorov
 * @version 1.9
 */
public class CSVReader {

    private final Path path;
    private final String delimiter;
    private final String out;

    private final List<String> filter;

    private final StringBuilder builder = new StringBuilder();
    private static final Logger LOG = LoggerFactory.getLogger(CSVReader.class.getName());

    public CSVReader(Path path, String delimiter, String out, String[] filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = List.of(filter);
    }

    /**
     * Метод подготавливает данные в соответствии с фильтром
     * <p>
     * tableHeader массив с названиями столбцов
     * index массив с индексами столбцов, соответствующих фильтру
     * rows массив с элементами из столбцов
     * builder собирает отфильтрованные элементы в строки
     */
    public void dataPrepare() {
        try (var scan = new Scanner(path)) {
            final String[] tableHeader = scan.next().split(delimiter);
            int[] indexes = IntStream.range(0, tableHeader.length)
                    .filter(idx -> filter.contains(tableHeader[idx])).toArray();
            rowsAppender(tableHeader, indexes);
            builder.append(System.lineSeparator());
            while (scan.hasNext()) {
                final String[] rows = scan.next().split(delimiter);
                rowsAppender(rows, indexes);
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            LOG.error("dataPrepare...", e);
        }

    }

    /**
     *
     * @param tableRow массив - первая строка таблицы с наименованиями столбцов
     * @param indexes массив с индексами столбцов, соответствующих фильтру
     */
    private void rowsAppender(String[] tableRow, int[] indexes) {
        for (int j : indexes) {
            builder.append(tableRow[j]);
            if (j != indexes.length - 1) {
                builder.append(";");
            }
        }
    }

    /**
     * Метод, осуществляющий вывод либо в консоль, либо в файл
     */
    public void dataOutput() {
        if (out.equals("stdout")) {
            println(builder);
        } else {
            try (var writer = new FileWriter(out)) {
                writer.write(builder.toString());
            } catch (IOException e) {
                LOG.error("Output error", e);
            }
        }
    }

    /**
     * Класс, методы которого производят валидацию входных аргументов
     */
    static class ValidateArgs {
        private final ArgsName argsParse;

        public ValidateArgs(String[] args) {
            argsParse = ArgsName.of(args);
        }

        public String path() {
            return argsParse.get("path");
        }

        public String delimiter() {
            return argsParse.get("delimiter");
        }

        public String out() {
            return argsParse.get("out");
        }

        public String filter() {
            return argsParse.get("filter");
        }

        public boolean isValid() {
            try {
                path();
                delimiter();
                out();
                filter();
            } catch (IllegalArgumentException e) {
                LOG.error("Illegal arguments", e);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        var validArgs = new ValidateArgs(args);
        if (validArgs.isValid()) {
            final var reader = new CSVReader(
                    Path.of(validArgs.path()),
                    validArgs.delimiter(),
                    validArgs.out(),
                    validArgs.filter().split(",")
            );
            reader.dataPrepare();
            reader.dataOutput();
        }
    }
}