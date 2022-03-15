package cryptography.dictionaries;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class FillingDictionary {
    public static Path dictionary = Path.of("src/com.java.javarush/cryptography/dictionaries/dictionaryForEnrypt.txt");

    public static void filling(Path source) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(source)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(dictionary)))) {
            Set<String> words = new HashSet<String>();
            while (reader.ready()) {
                String input = reader.readLine();
                for(String word : input.split("[ ,.!?\"+«»]")) {
                    words.add(word);
                }
            }
            for(String word : words) {
                System.out.println(word);
            }
            for (var word : words) {
                writer.write(word + "\n");
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
