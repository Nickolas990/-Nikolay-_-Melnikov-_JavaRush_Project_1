package com.java.javarush.main.cryptography.dictionaries;



public class CircleOfLetters {
    private final char[] alphabet = "àáâãäå¸æçèéêëìíîïğñòóôõö÷øùúûüışÿ.,\":-!? ".toCharArray();
    private int normalKey;

    public char[] getAlphabet() {
        return alphabet;
    }

    public int getNormalKey() {
        return normalKey;
    }

    public void setNormalKey(int userKey) {
        this.normalKey = normalizeKey(userKey);
    }

    private int normalizeKey(int userKey) {
        int key;
        if (userKey > alphabet.length) {
            key = userKey % alphabet.length;
        } else key = userKey;
        return key;
    }
}