package ru.job4j.io.searchfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Класс - создание предиката поиска файлов <p>
 * <p>
 *
 * @author Alex Gutorov
 * @version 1.1
 * <p>
 *
 */
public class CreateSearchPredicate {
    private static final Logger LOG = LoggerFactory.getLogger(CreateSearchPredicate.class.getName());

    /**
     *
     * @param searchType тип поиска (имя, маска, регулярное выражение)
     * @param fileSearchArg параметр поиска
     * @return метод возвращает предикат
     * Метод проверяет тип поиска и исходя из этого подготавливает имя или шаблон имени файла
     * Если на вход приходит имя, вызывается метод foundName
     * Если на вход приходит регулярное выражение, вызывается метод foundRegex
     * Если на вход приходит маска, она преобразуется в регулярное выражение в методе
     * maskToRegex и передается в метод foundRegex
     */
    public Predicate<Path> searchType(String searchType, String fileSearchArg) {
        Predicate<Path> result = null;
        try {
            if (searchType.equals("name")) {
                result = foundName(fileSearchArg);
            }
            if (searchType.equals("regex")) {
                result = foundRegex(fileSearchArg);
            }
            if (searchType.equals("mask")) {
                result = foundRegex(maskToRegex(fileSearchArg));
            }
        } catch (IllegalArgumentException e) {
            LOG.error("CheckArgs.searchType: Search type error", e);
        }
        return result;
    }

    /**
     *
     * @param file принимает на входе имя файла
     * @return возвращает предикат поиска
     * в лямбде проверяется соответствие имени файла принятому на входе имени
     */
    private Predicate<Path> foundName(String file) {
        return foundName -> foundName.toFile().getName().equals(file);
    }

    /**
     *
     * @param file принимает на входе регулярное выражение, соответствующее имени файла
     * @return возвращает предикат поиска
     * В методе происходит компиляция из регулярного выражения в имя файла и выполняется проверка
     * соответствия имени файла скомпилированному имени
     */
    private Predicate<Path> foundRegex(String file) {
        return foundRegex -> {
            var pattern = Pattern.compile(file);
            var matcher = pattern.matcher(foundRegex.toFile().getName());
            return matcher.find();
        };
    }

    /**
     *
     * @param mask принимает на входе маску, соответствующую имени файла
     * @return возвращает предикат
     * метод проебразует маску, принятую на входе в регулярное выражение
     */
    private String maskToRegex(String mask) {
        var builder = new StringBuilder();
        for (var i = 0; i < mask.length(); i++) {
            var symbol = mask.charAt(i);
            if (symbol == '*') {
                builder.append(".*");
            } else if (symbol == '.') {
                builder.append("\\.");
            } else {
                builder.append(symbol);
            }
        }
        return builder.toString();
    }
}
