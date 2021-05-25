package ru.job4j.io.searchfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CreateSearchPredicate {
    private static final Logger LOG = LoggerFactory.getLogger(CreateSearchPredicate.class.getName());

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

    private Predicate<Path> foundName(String file) {
        return foundName -> foundName.toFile().getName().equals(file);
    }

    private Predicate<Path> foundRegex(String file) {
        return foundRegex -> {
            var pattern = Pattern.compile(file);
            var matcher = pattern.matcher(foundRegex.toFile().getName());
            return matcher.find();
        };
    }

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
