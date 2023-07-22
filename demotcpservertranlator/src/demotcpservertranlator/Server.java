//server
package demotcpservertranlator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * This class, Server, represents the TCP server application for a text translator. 
 * It listens on port 4010 for client
 * connections and translates the incoming text based on the specified language code.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4011);
            System.out.println("Server started and listening on port 4010");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String text = reader.readLine();
                int languageCode = Integer.parseInt(reader.readLine());

                String translatedText = translate(text, languageCode);
                writer.println(translatedText);

                System.out.println("Translated Text: " + translatedText);

                clientSocket.close();
                System.out.println("Connection closed with the client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String translate(String text, int languageCode) {
        // Create a Map to store the translations for each language
        Map<Integer, Map<String, String>> translations = new HashMap<>();

        // English translations
        Map<String, String> enTranslations = new HashMap<>();
        enTranslations.put("Good morning", "Good morning");
        enTranslations.put("Good afternoon", "Good afternoon");
        enTranslations.put("Good evening", "Good evening");
        enTranslations.put("How are you?", "How are you?");
        enTranslations.put("Thank you", "Thank you");
        enTranslations.put("Goodbye", "Goodbye");

        // Bahasa Malaysia translations
        Map<String, String> msTranslations = new HashMap<>();
        msTranslations.put("Good morning", "Selamat pagi");
        msTranslations.put("Good afternoon", "Selamat petang");
        msTranslations.put("Good evening", "Selamat malam");
        msTranslations.put("How are you?", "Apa khabar?");
        msTranslations.put("Thank you", "Terima kasih");
        msTranslations.put("Goodbye", "Selamat tinggal");

        // Arabic translations
        Map<String, String> arTranslations = new HashMap<>();
        arTranslations.put("Good morning", "صباح الخير");
        arTranslations.put("Good afternoon", "مساء الخير");
        arTranslations.put("Good evening", "مساء الخير");
        arTranslations.put("How are you?", "كيف حالك؟");
        arTranslations.put("Thank you", "شكراً لك");
        arTranslations.put("Goodbye", "وداعاً");

        // Add the translations to the main Map
        translations.put(1, enTranslations);
        translations.put(2, msTranslations);
        translations.put(3, arTranslations);

        // Get the appropriate translations based on the language code
        Map<String, String> selectedTranslations = translations.get(languageCode);

        // Return the translated text if available, otherwise return the original text
        return selectedTranslations != null ? selectedTranslations.getOrDefault(text, text) : text;
    }
}
