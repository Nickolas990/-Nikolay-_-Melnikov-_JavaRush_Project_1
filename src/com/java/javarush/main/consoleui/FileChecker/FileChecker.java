package com.java.javarush.main.consoleui.FileChecker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FileChecker {
    private static FileChecker instance = new FileChecker();
    private final String WARNING =
            """
                This path is not safe for your system.
                Using system directories and system files could make damage to your OS.
            """;

    public static FileChecker getInstance() {
        return instance;
    }

    public String validate (String filepath)  throws IllegalArgumentException {
        if (check(filepath)) {
            throw new IllegalArgumentException("This file can not be changed");
        } else return filepath;
    }
    public boolean check(String filepath) {
        Path file = Path.of(filepath);
        Set<Path> directories = new HashSet<>();
        for (Path directory : file) {
            directories.add(directory);
        }
        if (directories.contains("windows") || directories.contains("etc") || directories.contains("hosts")) {
            System.out.println(WARNING);
            return true;
        }
        if (Files.isDirectory(file)) {
            return true;
        }
        return false;
    }
    private FileChecker() {
    }
}
