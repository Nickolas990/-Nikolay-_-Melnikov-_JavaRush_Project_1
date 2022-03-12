package dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dialog {
    private static final String GREETINGS = "Hello please choose option, that you need to use.";
    public static void start() {
        System.out.println(GREETINGS);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = reader.readLine();
            if (answer.equalsIgnoreCase("crypt")) cryptography.Crypt.start();
                else if(answer.equalsIgnoreCase("encrypt")) cryptography.EnCrypt.start();
                    else System.out.println("Incorrect command. Rerun CryptoScan and try again.");
        }catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
