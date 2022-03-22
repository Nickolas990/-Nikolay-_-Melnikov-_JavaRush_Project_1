package com.java.javarush.main.consoleui;

import com.java.javarush.main.cryptography.Cryption;
import com.java.javarush.main.cryptography.EnCryption;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;

public class Dialog {
    private static final String GREETINGS = "Hello, this is application for crypting and encrypting text.";
    private static final String ASKING_CRYPTOFILE = "Enter path to file with crypted file.";
    private static final String ASKING_ENCRYPTEDFILE = "Enter path to file for encrypted text.";
    private static final String ERROR = "Incorrect command. Rerun CryptoScan and try again";
    private static final String WARNING = "This path is not safe for your system. If you sure in your actions, enter this path again.";


    public static boolean check(String str) {
        Path path = Path.of(str);
        var set = new HashSet<String>();
        for (Path p : path) {
            set.add(p.toString());
        }
        if (set.contains("windows") || set.contains("etc") || set.contains("hosts")) {
            System.out.println(WARNING);
            return true;
        }
        return false;
    }



    public static void start() {
        System.out.println(GREETINGS);
        System.out.println("Please, choose one function of menu.");
        System.out.println("Enter 1 for crypting sequence.");
        System.out.println("Enter 2 for encrypting");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();

            if (answer.equalsIgnoreCase("1")) {

                System.out.println("Please, enter the path of file with text to make cipher");


                String str = reader.readLine();
                Path file;
                if (check(str)) {
                    file = Path.of(reader.readLine());
                } else  {
                    file = Path.of(str);
                }

                System.out.println("Please, enter the path of crypto file");

                str = reader.readLine();
                Path resultFile;
                if (check(str)) {
                    resultFile = Path.of(reader.readLine());
                } else  {
                    resultFile = Path.of(str);
                }

                System.out.println("Please, enter the key of cipher");
                int key = Integer.parseInt(reader.readLine());

                Cryption.start(file, key, resultFile);
                System.out.println("Crypted");

            } else if (answer.equalsIgnoreCase("2")) {
                System.out.println("Do you know crypt key? Y/N");
                if (reader.readLine().equalsIgnoreCase("N")) {
                    System.out.println("Please, choose method of encrypting");
                    System.out.println("For BruteForce enter 1, for StatisticEncrypting enter 2");
                    System.out.println("NOTICE: For StatisticEncrypting you will need example text from author of crypted text");
                    String encryptMethod = reader.readLine();


                    if ("1".equalsIgnoreCase(encryptMethod)) {
                    System.out.println(ASKING_CRYPTOFILE);
                    String str = reader.readLine();
                    Path file;
                    if (check(str)) {
                        file = Path.of(reader.readLine());
                    } else  {
                        file = Path.of(str);
                    }

                    System.out.println(ASKING_ENCRYPTEDFILE);
                    str = reader.readLine();
                    Path resultFile;
                    if (check(str)) {
                        resultFile = Path.of(reader.readLine());
                    } else  {
                        resultFile = Path.of(str);
                    }
                        EnCryption.usingBruteForce(file, resultFile);

                    } else if ("2".equals(encryptMethod)) {
                        System.out.println(ASKING_CRYPTOFILE);
                        String str = reader.readLine();
                        Path file;
                        if (check(str)) {
                            file = Path.of(reader.readLine());
                        } else  {
                            file = Path.of(str);
                        }

                        System.out.println(ASKING_ENCRYPTEDFILE);
                        str = reader.readLine();
                        Path resultFile;
                        if (check(str)) {
                            resultFile = Path.of(reader.readLine());
                        } else  {
                            resultFile = Path.of(str);
                        }
                        System.out.println("Enter path to exampleFile");
                        str = reader.readLine();
                        Path example;
                        if (check(str)) {
                            example = Path.of(reader.readLine());
                        } else  {
                            example = Path.of(str);
                        }
                        EnCryption.usingStatisticEncryption(example, file, resultFile);
                    } else System.out.println(ERROR);

                } else if (reader.readLine().equalsIgnoreCase("Y")) {
                    System.out.println(ASKING_CRYPTOFILE);
                    String str = reader.readLine();
                    Path file;
                    if (check(str)) {
                        file = Path.of(reader.readLine());
                    } else  {
                        file = Path.of(str);
                    }

                    System.out.println(ASKING_ENCRYPTEDFILE);

                    str = reader.readLine();
                    Path resultFile;
                    if (check(str)) {
                        resultFile = Path.of(reader.readLine());
                    } else  {
                        resultFile = Path.of(str);
                    }

                    System.out.println("Enter the key for encrypt");
                    int key = Integer.parseInt(reader.readLine());
                    EnCryption.startStandartEncryption(file, key, resultFile);
                }
            } else System.out.println(ERROR);
        } catch (IOException e) {
            System.out.println("Incorrect data entered");
        }
    }
}
