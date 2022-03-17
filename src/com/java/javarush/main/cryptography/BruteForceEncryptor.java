package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.file.Path;

public class BruteForceEncryptor extends EnCryptor {
    private Path crypted;

    public BruteForceEncryptor(Path crypted) {
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
