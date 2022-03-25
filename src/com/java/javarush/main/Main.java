package com.java.javarush.main;

import com.java.javarush.main.consoleui.Dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String GREETINGS = "This is application for crypting and encrypting text. Press \"Enter\" to start or\n";
    private static final String EXIT = "Type \"Exit\" to exit program";


    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(GREETINGS + EXIT);
            String enterOrExit = reader.readLine();

            while (!"exit".equalsIgnoreCase(enterOrExit)) {
                Dialog.start();
                System.out.println();
                System.out.println(GREETINGS + EXIT);
                enterOrExit = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Incorrect command");
        }
    }
}
