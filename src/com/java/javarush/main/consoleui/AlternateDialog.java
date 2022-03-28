package com.java.javarush.main.consoleui;

import com.java.javarush.main.consoleui.FileChecker.FileChecker;
import com.java.javarush.main.cryptography.BruteForceEncryptor;
import com.java.javarush.main.cryptography.EnCryptor;
import com.java.javarush.main.cryptography.StatisticEncryptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class AlternateDialog {
    Path input;
    Path output;
    Path example;
    int key;
    FileChecker checker = FileChecker.getInstance();
    private static final String ASKING_OUTPUT = "Enter path to file with result.";
    private static final String ASKING_INPUT = "Enter path to file for work with";
    private static final String ASKING_FOR_KEY = "Enter the key for crypting";
    private static final String ASKING_FOR_EXAMPLE = """
               NOTICE: For StatisticEncrypting you will need example text from author of crypted text
               Enter the path to example file
            """;
    private static final String ERROR = "Incorrect command. Rerun CryptoScan and try again";
    private static final String EXIT = "Type \"Exit\" to exit program";


    public void showMenu() {
        for (Options option : Options.values()) {
            System.out.printf("%d - %s", option.getNum(), option.getDescription());
            System.out.println();
        }
    }

    public void askForFiles() {
        try {
            BufferedReader pathsReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println(ASKING_INPUT);
            String filename = pathsReader.readLine();
            checker.validate(filename);
            input = Path.of(filename);

            System.out.println(ASKING_OUTPUT);
            filename = pathsReader.readLine();
            checker.validate(filename);
            output = Path.of(filename);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println("Filepath is incorrect");
        }
    }

    public void askForFilesAndKey() {
        askForFiles();
        System.out.println(ASKING_FOR_KEY);
        try {
            BufferedReader pathsReader = new BufferedReader(new InputStreamReader(System.in));
            key = Integer.parseInt(pathsReader.readLine());
        } catch (IOException e) {
            System.out.println("Key is incorrect " + e.getMessage());
        }
    }

    public void askForExample() {
        System.out.println(ASKING_FOR_EXAMPLE);
        try {
            BufferedReader pathsReader = new BufferedReader(new InputStreamReader(System.in));
            var path = checker.validate(pathsReader.readLine());
            example = Path.of(path);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("This file cannot be example or this is directory");
        }
    }

    public int makeDecision() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Incorrect choice");
            return 0;
        }
    }

    public void decide(int decision) {
        switch (decision) {
            case 1: {
                askForFilesAndKey();
                var cryptor = new EnCryptor(key, input, output, true);
                cryptor.process();
            }
            break;
            case 2: {
                askForFilesAndKey();
                var enCryptor = new EnCryptor(key, input, output, false);
                enCryptor.process();
            }
            break;
            case 22: {
                askForFiles();
                var bruteForce = new BruteForceEncryptor(input, output);
                bruteForce.process();
            }
            break;
            case 23: {
                askForFiles();
                askForExample();
                var statisticEncryptor = new StatisticEncryptor(example, input, output);
                statisticEncryptor.process();
            }
            break;
            case 0:
                return;
            default:
                System.out.println("Incorrect command");
        }
    }

    public void start() {
        showMenu();
        decide(makeDecision());
    }
}
