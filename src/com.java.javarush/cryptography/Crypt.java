package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class Crypt {
    private static int key = 0;
    private static final char[] alphabet = CircleOfLetters.init();
    public static void start(RandomAccessFile fileToCrypt, int userKey, RandomAccessFile cryptoFile) {
        try (FileChannel channel = fileToCrypt.getChannel();
             FileChannel cryptoChannel = cryptoFile.getChannel()) {
            key = userKey;
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            int read = channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static char crypt (char letter) {
        char cryptoLetter = ' ';
        for (int i = 0; i < alphabet.length; i++) {
            if (letter == alphabet[i]) {
                if ((i + key) < alphabet.length) letter = alphabet[i + key];
                else letter = alphabet[i + key - alphabet.length];
            }
        }

        return cryptoLetter;
    }
}
