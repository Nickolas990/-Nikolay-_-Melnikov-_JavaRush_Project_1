package com.java.javarush.main;

import com.java.javarush.main.consoleui.AlternateDialog;

public class Main {
    private static final String GREETINGS = "This is application for crypting and encrypting text. Press \"Enter\" to start or\n";
    private static final String EXIT = "Type \"Exit\" to exit program";


    public static void main(String[] args) {
        boolean isNeedReturn = true;
        while (isNeedReturn) {
            AlternateDialog dialog = new AlternateDialog();
            dialog.start();
            isNeedReturn = false;
        }
    }
}
