package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
        }
        if (args.length < 2 || args[1].isEmpty()) {
            throw new IllegalArgumentException("File extension is null");
        }

        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String str) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(str));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}