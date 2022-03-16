package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.file.Path;

public class BruteForceEncryptor extends EnCrypt {
    private char[] alphabet;
    private Path crypted;

    public BruteForceEncryptor(Path crypted) {
        this.alphabet = new CircleOfLetters().getAlphabet();
        this.crypted = crypted;
    }

    public void breaking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)))) {
            String input = reader.readLine();
            char[] charInput = input.toCharArray();
            for (int i = 0; i < alphabet.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (char c : charInput) {
                    sb.append(enCrypt(c, i));
                }
                System.out.println(i + " : " + sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
