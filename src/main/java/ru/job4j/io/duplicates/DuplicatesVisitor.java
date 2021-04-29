package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> foundFiles = new HashSet<>();
    private final List<Path> foundDuplicates = new ArrayList<>();

    public List<Path> getFoundDuplicates() {
        return foundDuplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(),
                file.toFile().getName());
        if (!foundFiles.contains(property)) {
            foundFiles.add(property);
        } else {
            foundDuplicates.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
