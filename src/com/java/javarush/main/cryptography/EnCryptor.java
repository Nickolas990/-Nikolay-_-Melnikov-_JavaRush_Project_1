package com.java.javarush.main.cryptography;

import com.java.javarush.main.cryptography.dictionaries.CircleOfLetters;

import java.nio.file.Path;

public class EnCryptor {
    int key = 0;
    CircleOfLetters lettersForEncryption = new CircleOfLetters();
    char[] alphabet = lettersForEncryption.getAlphabet();
    Path input;
    Path output;

    EnCryptor(){}

    public EnCryptor(int key, Path crypted, Path encrypted) {
        lettersForEncryption.setNormalKey(key);
        this.key = lettersForEncryption.getNormalKey();
        this.input = Path.of (String.valueOf(crypted));
        this.output = Path.of (String.valueOf(encrypted));
    }



    public char enCrypt(char letter, int key) {
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
    public char crypt (char letter) {
        char cryptoLetter = letter;
        String str = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (str.equalsIgnoreCase(alphabetic)) {
                if ((i + key) < alphabet.length){
                    cryptoLetter = alphabet[i + key];
                } else cryptoLetter = alphabet[i + key - alphabet.length];
            }
        }
        return cryptoLetter;
    }
}
