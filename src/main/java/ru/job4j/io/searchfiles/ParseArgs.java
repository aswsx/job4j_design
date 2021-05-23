package ru.job4j.io.searchfiles;

import java.util.HashMap;
import java.util.Map;
public class ParseArgs {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    public static ParseArgs of(String... args) {
        var names = new ParseArgs();
        names.parse(args);
        return names;
    }

    private void parse(String... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("ParseArgs.parse: Trying to parse empty args");
        }
        for (String arg : args) {
            String[] split = arg.replaceFirst("-", "").split("=");
            if (split.length < 2) {
                throw new IllegalArgumentException("ParseArgs.parse: Args is not correct");
            }
            values.put(split[0], split[1]);
        }
    }
}



