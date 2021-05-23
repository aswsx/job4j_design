package ru.job4j.io.searchfiles;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CheckArgs {
    private final ParseArgs parseArgs;

    public CheckArgs(String... args) {
        parseArgs = ParseArgs.of(args);
    }

    public String directory() {
        return parseArgs.get("d");
    }

    public String nameMaskRegex() {
        return parseArgs.get("n");
    }

    public String searchType() {
        return parseArgs.get("t");
    }

    public String output() {
        return parseArgs.get("o");
    }

    public boolean isValid() {
        try {
            directory();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            nameMaskRegex();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            searchType();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            output();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Predicate<Path> searchType(String searchType, String fileSearchArg) {
        if (searchType.equals("name")) {
            return foundName(fileSearchArg);
        }
        if (searchType.equals("regex")) {
            return foundRegex(fileSearchArg);
        }
        if (searchType.equals("mask")) {
            return foundRegex(maskToRegex(fileSearchArg));
        }
        throw  new IllegalArgumentException("CheckArgs.searchType: Search type error");
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
                builder.append("//.");
            } else {
                builder.append(symbol);
            }
        }
        return builder.toString();
    }
}
