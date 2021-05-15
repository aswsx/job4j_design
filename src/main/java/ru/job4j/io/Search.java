package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        if (args.length < 2) {
            throw new IllegalArgumentException("File extension is null");
        }
        Path start = Paths.get(args[0]);
        search(p -> p.toFile().getName().endsWith(args[1]), start)
                .forEach(System.out::println);
    }

    public static List<Path> search(Predicate<Path> func, Path root) throws IOException {
        SearchFiles searcher = new SearchFiles(func);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}