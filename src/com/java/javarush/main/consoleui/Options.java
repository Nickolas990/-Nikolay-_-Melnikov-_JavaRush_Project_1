package com.java.javarush.main.consoleui;

public enum Options {
    CRYPT_MODE(1, "Cryption"),
    ENCRYPTION(2, "Encryption"),
    BRUTEFORCE(22, "BruteForce"),
    STATISTICENCRYPTION(23, "StatisticEncryptor"),
    EXIT(0, "EXIT");

    private final int num;
    private final String description;

    public int getNum() {
        return num;
    }

    public String getDescription() {
        return description;
    }

    Options(int num, String text) {
        this.num = num;
        description = text;
    }
}
