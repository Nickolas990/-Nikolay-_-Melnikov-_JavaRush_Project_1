package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class BruteForceEncryptor {
    private char[] alphabet;
    private Path crypted;
    private Path encrypted;

    public BruteForceEncryptor(Path crypted, Path encrypted) {
        this.alphabet = new CircleOfLetters().getAlphabet();
        this.crypted = crypted;
        this.encrypted = encrypted;
    }

    public void breaking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {
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

    private char enCrypt(char letter, int key) {
        char cryptoLetter = letter;
        String str = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (str.equalsIgnoreCase(alphabetic)) {
                if ((i - key) >= 0) {
                    cryptoLetter = alphabet[i - key];
                } else cryptoLetter = alphabet[i - key + alphabet.length];
            }
        }
        return cryptoLetter;
    }


}
