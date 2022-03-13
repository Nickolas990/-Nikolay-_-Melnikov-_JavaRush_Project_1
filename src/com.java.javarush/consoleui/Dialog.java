package consoleui;

import java.io.*;
import java.nio.file.Path;

public class Dialog {
    private static final String GREETINGS = "Hello please choose option, that you need to use.";
    public static void start() {
        System.out.println(GREETINGS);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (answer.equalsIgnoreCase("crypt")) {
                System.out.println("Please, enter the path of file with text to make cipher");
                Path file = Path.of(reader.readLine());
                System.out.println("Please, enter the key of cipher");
                int key = Integer.parseInt(reader.readLine());
                System.out.println("Please, enter the path of crypto file");
                Path resultFile = Path.of(reader.readLine());
                cryptography.Crypt.start(file, key, resultFile);
            }
                else if(answer.equalsIgnoreCase("encrypt")) cryptography.EnCrypt.start();
                    else System.out.println("Incorrect command. Rerun CryptoScan and try again.");
        }catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
