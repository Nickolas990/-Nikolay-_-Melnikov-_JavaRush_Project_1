package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class EnCrypt {
    private static int key = 0;
    private static char[] alphabet;

    public static void startStandartEncryption(Path crypted, int userKey, Path encrypted) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {
            CircleOfLetters circleOfLetters = new CircleOfLetters();
            circleOfLetters.setNormalKey(userKey);
            alphabet = circleOfLetters.getAlphabet();
            key = circleOfLetters.getNormalKey();
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while (cBuff.hasRemaining()) {
                    char c = cBuff.get();
                    System.out.print(enCrypt(c, key));
                    writer.write(enCrypt(c, key));
                }
                System.out.println();
                cBuff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void usingBruteForce(Path crypted, Path encrypted) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BruteForceEncryptor bruteForcer = new BruteForceEncryptor(crypted);
            System.out.println("Here all possible variants of encrypting. Choose fit variant");
            bruteForcer.breaking();
            int userKey = Integer.parseInt(reader.readLine());
            startStandartEncryption(crypted, userKey, encrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static char enCrypt(char letter, int key) {
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
