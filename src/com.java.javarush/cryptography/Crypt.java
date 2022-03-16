package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class Crypt {
    private static int key;
    private static char[] alphabet;
    public static void start(Path fileToCrypt, int userKey, Path cryptoFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(fileToCrypt)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(cryptoFile)))) {
            CircleOfLetters circleOfLetters = new CircleOfLetters();
            circleOfLetters.setNormalKey(userKey);
            alphabet = circleOfLetters.getAlphabet();
            key = circleOfLetters.getNormalKey();
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while(cBuff.hasRemaining()) {
                    char c = cBuff.get();
                    System.out.print(crypt(c, key));
                    writer.write(crypt(c, key));
                }
                System.out.println();
                cBuff.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static char crypt (char letter, int key) {
        char cryptoLetter = letter;
        String str = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (str.equalsIgnoreCase(alphabetic)) {
                if ((i + key) < alphabet.length){
                    cryptoLetter = alphabet[i + key];
                } else cryptoLetter = alphabet[i + key - alphabet.length];
            }
        }
        return cryptoLetter;
    }
}
