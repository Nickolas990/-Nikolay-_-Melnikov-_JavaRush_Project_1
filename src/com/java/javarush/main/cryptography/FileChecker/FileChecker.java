package com.java.javarush.main.cryptography.FileChecker;

public class FileChecker {
    private static final String WARNING = "This path is not safe for your system.\n" +
            "Using system directories and system files could make damage to your OS. \n " +
            "If you sure in your actions, enter this path again. Or enter another.";

    public static boolean check(String filepath) {
        if (filepath.contains("windows") || filepath.contains("etc") || filepath.contains("hosts")) {
            System.out.println(WARNING);
            return true;
        }
        return false;
    }
}
