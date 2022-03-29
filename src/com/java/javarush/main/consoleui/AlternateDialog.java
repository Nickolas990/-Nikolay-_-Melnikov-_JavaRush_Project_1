package com.java.javarush.main.consoleui;

import com.java.javarush.main.consoleui.FileChecker.FileChecker;
import com.java.javarush.main.consoleui.exceptions.InvalidUserInputException;
import com.java.javarush.main.cryptography.BruteForceDecryptor;
import com.java.javarush.main.cryptography.EnCryptor;
import com.java.javarush.main.cryptography.StatisticDecryptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class AlternateDialog {
    public static final String AGAIN_MESSAGE = "again";
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

    private final BufferedReader in;

    public AlternateDialog() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        showMenu();
        decide(readOption());
    }

    public void showMenu() {
        for (Options option : Options.values()) {
            System.out.printf("%d - %s", option.getNum(), option.getDescription());
            System.out.println();
        }
    }




    private void askForFiles() {
        try {
            System.out.println(ASKING_INPUT);
            String filename = checker.validate(in.readLine());
            input = Path.of(filename);

            System.out.println(ASKING_OUTPUT);
            filename = checker.validate(in.readLine());
            output = Path.of(filename);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (IOException e) {
            System.out.println("Filepath is incorrect" + e.getMessage());
        }
    }

    private void askForFilesAndKey() {
        askForFiles();
        System.out.println(ASKING_FOR_KEY);
        key = readInt();
    }

    private void askForExample() {
        System.out.println(ASKING_FOR_EXAMPLE);
        try {
            var path = checker.validate(in.readLine());
            example = Path.of(path);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("This file cannot be example or this is directory");
        }
    }

    private void decide(Options option) {

        switch (option) {
            case CRYPT_MODE -> encriptionProcess();
            case ENCRYPTION -> decryptProcess();
            case BRUTEFORCE -> bruteForceProcess();
            case STATISTICENCRYPTION -> statisticProcess();

            case EXIT -> processExit();
        }
    }

    private int readInt() {
        try {
            String input = in.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException | IOException e) {
            throw new InvalidUserInputException("Input is incorrect", e);
        }
    }

    private String readString() {
        try {
            String input = in.readLine();
            return input;
        } catch (IOException e) {
            throw new InvalidUserInputException("String is incorrect", e);
        }
    }

    private Options readOption() {
        boolean isNeedRepeat;
        do {
            isNeedRepeat = false;
            try {
                int option = readInt();
                return Options.getOptionByNum(option);
            } catch (IllegalArgumentException | InvalidUserInputException e) {
                System.out.println("Wrong operation chosen.");
                System.out.println("Reason:" + e.getMessage());
                System.out.println("Enter \"again\" for repeat, or any key for exit");
                String input = readString();
                if (AGAIN_MESSAGE.equalsIgnoreCase(input)) {
                    isNeedRepeat = true;
                }
            }
        } while (isNeedRepeat);
        return Options.EXIT;
    }

    private void encriptionProcess() {
        askForFilesAndKey();
        var cryptor = new EnCryptor(key, input, output, true);
        cryptor.process();
    }

    private void decryptProcess() {
        askForFilesAndKey();
        var deCryptor = new EnCryptor(key, input, output, false);
        deCryptor.process();
    }

    private void bruteForceProcess() {
        askForFiles();
        var bruteForce = new BruteForceDecryptor(input, output);
        bruteForce.process();
    }

    private void statisticProcess() {
        askForFiles();
        askForExample();
        var statisticEncryptor = new StatisticDecryptor(example, input, output);
        statisticEncryptor.process();
    }

    private void processExit() {
        System.out.println("Bye!");
    }
}
