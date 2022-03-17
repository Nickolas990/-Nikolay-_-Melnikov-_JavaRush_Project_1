package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class Cryption {

    public static void start(Path crypted, int userKey, Path encrypted) {
        EnCryptor enCryptor = new EnCryptor(userKey, crypted, encrypted);
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while(cBuff.hasRemaining()) {
                    char c = cBuff.get();
                    System.out.print(enCryptor.crypt(c));
                    writer.write(enCryptor.crypt(c));
                }
                System.out.println();
                cBuff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
