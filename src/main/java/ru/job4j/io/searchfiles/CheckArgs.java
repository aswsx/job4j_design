package ru.job4j.io.searchfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckArgs {
    private final ParseArgs parseArgs;
    private static final Logger LOG = LoggerFactory.getLogger(CheckArgs.class.getName());

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
            nameMaskRegex();
            searchType();
            output();
        } catch (IllegalArgumentException e) {
            LOG.error("Arguments is not valid", e);
        }
        return true;
    }
}
