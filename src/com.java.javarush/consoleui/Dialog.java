package consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class Dialog {
    private static final String GREETINGS = "Hello please choose option, that you need to use.";
    public static void start() {
        System.out.println(GREETINGS);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (answer.equalsIgnoreCase("crypt")) {
                System.out.println("Please, enter the path of file with text to make cipher");
                RandomAccessFile file = new RandomAccessFile(reader.readLine(), "rw");
                System.out.println("Please, enter the key of cipher");
                int key = Integer.parseInt(reader.readLine());
                System.out.println("Please, enter the path of crypto file");
                RandomAccessFile resultFile = new RandomAccessFile(reader.readLine(), "rw");
                cryptography.Crypt.start(file, key, resultFile);
            }
                else if(answer.equalsIgnoreCase("encrypt")) cryptography.EnCrypt.start();
                    else System.out.println("Incorrect command. Rerun CryptoScan and try again.");
        }catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
