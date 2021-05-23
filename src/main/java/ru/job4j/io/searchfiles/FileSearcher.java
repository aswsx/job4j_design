package ru.job4j.io.searchfiles;

import ru.job4j.collection.List;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class FileSearcher {
    public static List<Path> search(Predicate<Path> func, Path root) throws IOException {
        var searcher = new FileVisitor(func);
        Files.walkFileTree(root, searcher);
        return (List<Path>) searcher.getPaths();
    }

    public static void write(String target, List<Path> paths) {
        try (var writer = new BufferedWriter(
                new FileWriter(target, Charset.forName("WINDOWS-1251")))) {
            for (Path path : paths) {
                writer.write(path.toAbsolutePath().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            var checkArgs = new CheckArgs(args);
            if (checkArgs.isValid()) {
                var pathToSearchDirectory = Paths.get(checkArgs.directory());
                Predicate<Path> pathPredicate = new CheckArgs().searchType(
                        checkArgs.searchType(),
                        checkArgs.nameMaskRegex());
                List<Path> rsl = search(pathPredicate, pathToSearchDirectory);
                write(checkArgs.output(), rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
