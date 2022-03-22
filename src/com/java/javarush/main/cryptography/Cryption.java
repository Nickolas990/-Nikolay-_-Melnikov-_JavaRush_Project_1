package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class Cryption {

    public static void start(Path crypted, int userKey, Path encrypted) {
        EnCryptor enCryptor = new EnCryptor(userKey, crypted, encrypted);
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            while (reader.read(charBuffer) != -1) {
                charBuffer.flip();
                while (charBuffer.hasRemaining()) {
                    char c = charBuffer.get();
                    System.out.print(enCryptor.crypt(c));
                    writer.write(enCryptor.crypt(c));
                }
                System.out.println();
                charBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("Incorrect data entered");
        }
    }
}
