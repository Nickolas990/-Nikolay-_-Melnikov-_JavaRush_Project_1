package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;

public class EnCrypt {
    private static int key = 0;
    private static final char[] alphabet = CircleOfLetters.init();
    public static void start(Path crypted, int userKey, Path encrypted) {
        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(crypted)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(encrypted)))) {
            key = userKey;
            CharBuffer cBuff = CharBuffer.allocate(1024);
            while (reader.read(cBuff) != -1) {
                cBuff.flip();
                while(cBuff.hasRemaining()) {
                    char c = cBuff.get();
                    System.out.print(enCrypt(c));
                    writer.write(enCrypt(c));
                }
                System.out.println();
                cBuff.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static char enCrypt (char letter) {
        char cryptoLetter = letter;
        String str = String.valueOf(letter);
        for (int i = 0; i < alphabet.length; i++) {
            String alphabetic = String.valueOf(alphabet[i]);
            if (str.equalsIgnoreCase(alphabetic)) {
                if ((i - key) >= 0){
                    cryptoLetter = alphabet[i - key];
                    break;
                }
                else cryptoLetter = alphabet[i - key + alphabet.length];
                break;
            }
        }
        return cryptoLetter;
    }
}
