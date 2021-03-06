package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new NoSuchElementException("Element not found");
        }
        return value;
    }

    private void parse(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        for (String arg : args) {
            String[] split = arg.replaceFirst("-", "").split("=");
            if (split.length < 2) {
                throw new IllegalArgumentException();
            }
            values.put(split[0], split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        var names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        var jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        var zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}