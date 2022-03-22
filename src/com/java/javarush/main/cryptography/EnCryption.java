package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class EnCryption {

    public static void startStandartEncryption(Path crypted, int userKey, Path encrypted) {
        EnCryptor enCryptor = new EnCryptor(userKey, crypted, encrypted);
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {

            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while (cBuff.hasRemaining()) {
                    char c = cBuff.get();
                    System.out.print(enCryptor.enCrypt(c, enCryptor.key));
                    writer.write(enCryptor.enCrypt(c, enCryptor.key));
                }
                System.out.println();
                cBuff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void usingStatisticEncryption(Path example, Path crypted, Path encrypted) {
        StatisticEncryptor statsEcryptor = new StatisticEncryptor(example, crypted, encrypted);
        int userKey = statsEcryptor.breaking();
        startStandartEncryption(crypted, userKey, encrypted);
    }

    public static void usingBruteForce(Path crypted, Path encrypted) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            BruteForceEncryptor bruteForcer = new BruteForceEncryptor(crypted);
            System.out.println("Here all possible variants of encrypting. Enter number of fits variant");
            bruteForcer.breaking();
            int userKey = Integer.parseInt(reader.readLine());
            startStandartEncryption(crypted, userKey, encrypted);
        } catch (IOException e) {
            System.out.println("Incorrect data entered");
        }
    }
}
