package cryptography;

import cryptography.dictionaries.CircleOfLetters;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

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
            StringBuilder builder = new StringBuilder();
            while (buffer.hasRemaining()) {
                builder.append(crypt((char)buffer.get()));
            }
            System.out.println(builder);
            cryptoChannel.write(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Некорректно приходят данные в метод. letter должен быть кириллическим символом, однако не могу понять, что передается в метод в итоге
    private static char crypt (char letter) {
        char cryptoLetter = ' ';
        for (int i = 0; i < alphabet.length; i++) {
            if (letter == alphabet[i]) {
                if ((i + key) < alphabet.length) cryptoLetter = alphabet[i + key];
                else cryptoLetter = alphabet[i + key - alphabet.length];
            }
        }
        return cryptoLetter;
    }
}
