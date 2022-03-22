package com.java.javarush.main.cryptography.dictionaries;


public class CircleOfLetters {
    private final char[] alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ".toCharArray();
    private int normalKey;

    public char[] getAlphabet() {
        return alphabet;
    }

    public int getNormalKey() {
        return normalKey;
    }

    public void setNormalKey(int userKey) {
        if (userKey != 0) {
            this.normalKey = normalizeKey(userKey);
        } else this.normalKey = 0;
    }

    private int normalizeKey(int userKey) {
        int key;
        if (Math.abs(userKey) > alphabet.length) {
            key = userKey % alphabet.length;
        } else if (userKey < 0 && Math.abs(userKey) < alphabet.length) {
            key = alphabet.length + userKey;
        } else key = userKey;
        return key;
    }
}
