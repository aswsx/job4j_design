package ru.job4j.io.searchfiles;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FileVisitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> func;
    private final List<Path> paths;

    public FileVisitor(Predicate<Path> func) {
        this.func = func;
        paths = new ArrayList<>();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (func.test(file)) {
            paths.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
