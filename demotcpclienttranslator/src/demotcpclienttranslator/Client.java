//client
package demotcpclienttranslator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This class, Client, represents a client that connects to the Text Translator server to 
 * request translations
 * for selected sentences in different languages.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4011);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to the server.");

            while (true) {
                System.out.println("Select a sentence to translate (1-6):");
                System.out.println("1. Good morning");
                System.out.println("2. Good afternoon");
                System.out.println("3. Good evening");
                System.out.println("4. How are you?");
                System.out.println("5. Thank you");
                System.out.println("6. Goodbye");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }

                System.out.println("Select a target language code (1. English, 2. Bahasa Malaysia, 3. Arabic, 4. Korean):");

                int languageCode = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (languageCode < 1 || languageCode > 4) {
                    System.out.println("Unknown language code. Please try again.");
                    continue;
                }

                String text = getTextToTranslate(choice);
                writer.println(text);
                writer.println(languageCode);

                String translatedText = reader.readLine();
                System.out.println("Translated Text: " + translatedText);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTextToTranslate(int choice) {
        switch (choice) {
            case 1:
                return "Good morning";
            case 2:
                return "Good afternoon";
            case 3:
                return "Good evening";
            case 4:
                return "How are you?";
            case 5:
                return "Thank you";
            case 6:
                return "Goodbye";
            default:
                return "";
        }
    }
}
