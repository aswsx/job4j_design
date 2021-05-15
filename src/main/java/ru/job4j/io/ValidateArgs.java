package ru.job4j.io;

public class ValidateArgs {
    private final ArgsName argsParse;

    public ValidateArgs(String[] args) {
        argsParse = ArgsName.of(args);
    }

    public String dir() {
        return argsParse.get("d");
    }

    public String exclude() {
        return argsParse.get("e");
    }

    public String out() {
        return argsParse.get("o");
    }

    public boolean isValid() {
        try {
            dir();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            exclude();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            out();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return true;
    }
}
