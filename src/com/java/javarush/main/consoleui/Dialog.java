package com.java.javarush.main.consoleui;

import com.java.javarush.main.cryptography.Cryption;
import com.java.javarush.main.cryptography.EnCryption;

import java.io.*;
import java.nio.file.Path;

public class Dialog {
    private static final String GREETINGS = "Hello please choose option, that you need to use.";

    public static void start() {
        System.out.println(GREETINGS);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (answer.equalsIgnoreCase("crypt")) {
                System.out.println("Please, enter the path of file with text to make cipher");
                Path file = Path.of(reader.readLine());
                System.out.println("Please, enter the key of cipher");
                int key = Integer.parseInt(reader.readLine());
                System.out.println("Please, enter the path of crypto file");
                Path resultFile = Path.of(reader.readLine());
                Cryption.start(file, key, resultFile);
                System.out.println("Crypted");
            } else if (answer.equalsIgnoreCase("encrypt")) {
                System.out.println("Do you know crypt key?");
                if (reader.readLine().equalsIgnoreCase("no")) {
                    System.out.println("Enter path of file with crypted file");
                    Path file = Path.of(reader.readLine());
                    System.out.println("Enter path of file for encrypted text");
                    Path resultFile = Path.of(reader.readLine());
                    EnCryption.usingBruteForce(file, resultFile);

                } else if (reader.readLine().equalsIgnoreCase("Yes")) {
                    System.out.println("Enter path of file with crypted file");
                    Path file = Path.of(reader.readLine());
                    System.out.println("Enter path of file for encrypted text");
                    Path resultFile = Path.of(reader.readLine());
                    System.out.println("Enter the key for encrypt");
                    int key = Integer.parseInt(reader.readLine());
                    EnCryption.startStandartEncryption(file, key, resultFile);
                }
            } else System.out.println("Incorrect command. Rerun CryptoScan and try again.");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
