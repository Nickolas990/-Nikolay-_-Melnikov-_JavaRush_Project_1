package com.java.javarush.main.cryptography.dictionaries;


public class CircleOfLetters {
    private final char[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ".toCharArray();

    public char[] getAlphabet() {
        return alphabet;
    }

    public int normalizeKey(int userKey) {
        int key = 0;
        if (userKey != 0) {
            if (Math.abs(userKey) > alphabet.length) {
                key = userKey % alphabet.length;
            } else if (userKey < 0 && Math.abs(userKey) < alphabet.length) {
                key = alphabet.length + userKey;
            } else key = userKey;
        }
        return key;
    }
}
