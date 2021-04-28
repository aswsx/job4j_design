package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<Path> foundFiles = new HashSet<>();
    private final List<Path> foundDuplicates = new ArrayList<>();

    public List<Path> getFoundDuplicates() {
        return foundDuplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file != null && !foundFiles.add(file)) {
            foundDuplicates.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
