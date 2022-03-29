package com.java.javarush.main.cryptography;

import java.io.*;
import java.nio.file.Path;

public class BruteForceDecryptor extends EnCryptor {


    public BruteForceDecryptor(Path input, Path output) {
        this.input = input;
        this.output = output;
        breaking();
    }



    public void breaking() {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(input)))) {
            BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            char[] charInput = input.toCharArray();
            for (int i = 0; i < alphabet.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (char c : charInput) {
                    key = i;
                    sb.append(DeCrypt(c));

                }
                System.out.println(key + " : " + sb);
                System.out.println();
            }
            key = Integer.parseInt(keyReader.readLine());
        } catch (IOException e) {
            System.out.println("Incorrect data entered");
        }
    }
}
