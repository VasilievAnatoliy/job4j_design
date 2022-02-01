package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> hashMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        Path path = file.toAbsolutePath();
        if (hashMap.containsKey(fileProperty)) {
            hashMap.get(fileProperty).add(path);
        } else {
            List<Path> list = new ArrayList<>();
            list.add(path);
            hashMap.put(fileProperty, list);
        }
        return FileVisitResult.CONTINUE;
    }


    public List<Path> duplicates() {
        return hashMap.entrySet().stream()
                .filter(x -> x.getValue().size() > 1)
                .flatMap(x -> x.getValue().stream())
                .collect(Collectors.toList());
    }
}

