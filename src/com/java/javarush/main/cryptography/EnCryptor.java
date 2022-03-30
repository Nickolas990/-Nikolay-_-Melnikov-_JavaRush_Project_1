package com.java.javarush.main.cryptography;

import com.java.javarush.main.cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class EnCryptor {
    CircleOfLetters lettersForEncryption = new CircleOfLetters();
    char[] alphabet = lettersForEncryption.getAlphabet();
    int key;
    boolean isCrypting = false;

    Path input;
    Path output;

    public static final int CAPACITY = 1024;

    EnCryptor(){}

    public EnCryptor(int key, Path crypted, Path encrypted, boolean isCrypting) {
        this.key = lettersForEncryption.normalizeKey(key);
        this.input = Path.of (String.valueOf(crypted));
        this.output = Path.of (String.valueOf(encrypted));
        this.isCrypting = isCrypting;
    }

    public void process() {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(input)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(output)))) {
            CharBuffer charBuffer = CharBuffer.allocate(CAPACITY);
            while (reader.read(charBuffer) != -1) {
                charBuffer.flip();
                while (charBuffer.hasRemaining()) {
                    char c = charBuffer.get();
                    System.out.print(isCrypting ? crypt(c) : DeCrypt(c));
                    writer.write(isCrypting ? crypt(c) : DeCrypt(c));
                }
                System.out.println();
                charBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("Check this file " + e.getMessage());
        }
    }

    public char DeCrypt(char letter) {
        char cryptoLetter = letter;
        String charAsString = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (charAsString.equalsIgnoreCase(alphabetic)) {
                if ((i - key) >= 0) {
                    cryptoLetter = alphabet[i - key];
                } else cryptoLetter = alphabet[i - key + alphabet.length];
            }
        }
        return cryptoLetter;
    }

    public char crypt (char letter) {
        char cryptoLetter = letter;
        String charAsString = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (charAsString.equalsIgnoreCase(alphabetic)) {
                if ((i + key) < alphabet.length){
                    cryptoLetter = alphabet[i + key];
                } else cryptoLetter = alphabet[i + key - alphabet.length];
            }
        }
        return cryptoLetter;
    }
}
