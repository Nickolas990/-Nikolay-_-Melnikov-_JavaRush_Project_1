package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;

public class Crypt {
    private static int key = 0;
    private static final char[] alphabet = CircleOfLetters.init();
    public static void start(Path fileToCrypt, int userKey, Path cryptoFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(fileToCrypt)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(cryptoFile)))) {
            key = userKey;
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while(cBuff.hasRemaining()) {
                    System.out.print(crypt(cBuff.get()));
                }
                cBuff.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static char crypt (char letter) {
        char cryptoLetter = letter;
        for (int i = 0; i < alphabet.length; i++) {
            if (letter == alphabet[i]) {
                if ((i + key) < alphabet.length){
                    cryptoLetter = alphabet[i + key];
                    break;
                }
                else cryptoLetter = alphabet[i + key - alphabet.length];
                break;
            }
        }
        return cryptoLetter;
    }
}
