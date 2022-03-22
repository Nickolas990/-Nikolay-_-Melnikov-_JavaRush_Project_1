package com.java.javarush.main.consoleui;

import com.java.javarush.main.cryptography.Cryption;
import com.java.javarush.main.cryptography.EnCryption;
import com.java.javarush.main.cryptography.FileChecker.FileChecker;

import java.io.*;
import java.nio.file.Path;

public class Dialog {
    private static final String ASKING_OUTPUT = "Enter path to file with result.";
    private static final String ASKING_INPUT = "Enter path to file for work with";
    private static final String ASKING_FOR_KEY = "Enter the key for crypting";
    private static final String ERROR = "Incorrect command. Rerun CryptoScan and try again";
    private static final String EXIT = "Type \"Exit\" to exit program";

    FileChecker fileChecker = new FileChecker();

    Path input;
    Path output;
    int key;

    public Dialog() {
        askingForFiles();
    }

    public void askingForFiles() {
        try {
            BufferedReader pathsReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(ASKING_INPUT);
            String filename = pathsReader.readLine();
            if (fileChecker.check(filename)) {
                input = Path.of(pathsReader.readLine());
            } else {
                input = Path.of(filename);
            }

            System.out.println(ASKING_OUTPUT);

            filename = pathsReader.readLine();
            if (fileChecker.check(filename)) {
                output = Path.of(pathsReader.readLine());
            } else {
                output = Path.of(filename);
            }
        } catch (IOException e) {
            System.out.println("Filepath is incorrect");
        }

    }


    public static void start() {
        Dialog dialog = new Dialog();
        try {
            BufferedReader scanner = new BufferedReader(new InputStreamReader (System.in));
            System.out.printf("Please, choose one function of menu. \n" +
                    "Enter 1 for crypting sequence. " +
                    "Enter 2 for encrypting. \n" +
                    EXIT);
            System.out.println();
            String input = scanner.readLine();
            if ("1".equals(input)) {
                System.out.println(ASKING_FOR_KEY);
                try {
                    dialog.key = Integer.parseInt(scanner.readLine());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error, key have to be a digit");
                }
                Cryption.start(dialog.input, dialog.key, dialog.output);

            } else if ("2".equals(input)) {
                System.out.println("Do you know the key? Y/N");
                input = scanner.readLine();
                if ("Y".equalsIgnoreCase(input)) {
                    System.out.println(ASKING_FOR_KEY);
                    EnCryption.startStandartEncryption(dialog.input, dialog.key, dialog.output);
                } else if ("N".equalsIgnoreCase(input)) {
                    System.out.printf("Press 1 for BruteForce Encrypting \n" +
                            "Press 2 for StatisticEncryption \n");
                    input = scanner.readLine();
                    if ("1".equals(input)) {
                        EnCryption.usingBruteForce(dialog.input, dialog.output);
                    } else if ("2".equals(input)) {
                        System.out.println("NOTICE: For StatisticEncrypting you will need example text from author of crypted text");
                        System.out.println("Enter the path to example file");
                        Path example = Path.of(scanner.readLine());
                        EnCryption.usingStatisticEncryption(example, dialog.input, dialog.output);
                    } else {
                        System.out.println("Incorrect input, press ENTER and try again");
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Entered incorrect data. Press ENTER to try again");
        }
    }
}
