package com.java.javarush.main.consoleui.exceptions;

public class InvalidUserInputException extends RuntimeException {
        public InvalidUserInputException (String message, Throwable cause) {
            super (message, cause);
        }
}
