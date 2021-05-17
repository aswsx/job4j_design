package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> foundFiles = new HashSet<>();
    private final List<Path> foundDuplicates = new ArrayList<>();

    public List<Path> getFoundDuplicates() {
        return foundDuplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        var property = new FileProperty(file.toFile().length(),
                file.toFile().getName());
        if (!foundFiles.contains(property)) {
            foundFiles.add(property);
        } else {
            foundDuplicates.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
