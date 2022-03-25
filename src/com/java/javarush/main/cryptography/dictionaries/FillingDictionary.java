package com.java.javarush.main.cryptography.dictionaries;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class FillingDictionary {
    public static final Path DICTIONARY = Path.of("src/com.java.javarush/main.cryptography/dictionaries/dictionaryForEnrypt.txt");

    public static void filling(Path source) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(source)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(DICTIONARY)))) {
            Set<String> words = new HashSet<>();
            while (reader.ready()) {
                String input = reader.readLine();
                Collections.addAll(words, input.split("[ ,.!?\"+]"));
            }
            for (var word : words) {
                writer.write(word + "\n");
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in this directory");
        } catch (IOException e) {
            System.out.println("Incorrect data entered");
        }
    }
}
