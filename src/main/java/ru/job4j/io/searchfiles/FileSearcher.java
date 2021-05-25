package ru.job4j.io.searchfiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileSearcher {
    private static final Logger LOG = LoggerFactory.getLogger(FileSearcher.class.getName());

    public static List<Path> search(Predicate<Path> func, Path root) {
        List<Path> result = new ArrayList<>();
        try {
            var searcher = new FileVisitor(func);
            Files.walkFileTree(root, searcher);
            result = searcher.getPaths();
        } catch (IOException e) {
            LOG.error("File search error", e);
        }
        return result;
    }

    public static void write(String target, List<Path> paths) {
        try (var writer = new BufferedWriter(
                new FileWriter(target, Charset.forName("WINDOWS-1251")))) {
            for (Path path : paths) {
                writer.write(path.toAbsolutePath().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            LOG.error("File write error", e);
        }
    }

    public static void main(String[] args) {
        var checkArgs = new CheckArgs(args);
        if (checkArgs.isValid()) {
            var pathToSearchDirectory = Paths.get(checkArgs.directory());
            Predicate<Path> pathPredicate = new CreateSearchPredicate().searchType(
                    checkArgs.searchType(),
                    checkArgs.nameMaskRegex());
            List<Path> rsl = search(pathPredicate, pathToSearchDirectory);
            write(checkArgs.output(), rsl);
        }
    }
}
