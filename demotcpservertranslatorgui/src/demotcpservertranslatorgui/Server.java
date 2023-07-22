package demotcpservertranslatorgui;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * This class, Server, represents a TCP server that handles 
 * translations for selected sentences in different languages.
 * It works in conjunction with the ServerJFrame class to 
 * provide a graphical user interface for the server.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Server {
    private ServerJFrame serverFrame;
    private ServerSocket serverSocket;

    public Server(ServerJFrame serverFrame) {
        this.serverFrame = serverFrame;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(4011);
            System.out.println("Server started and listening on port 4011");

            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(serverFrame, "Server started and listening on port 4011", "Server Information", JOptionPane.INFORMATION_MESSAGE);
            });

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

                SwingUtilities.invokeLater(() -> {
                    serverFrame.updateRequestStatus("Translated text: " + translatedText);
                });

                clientSocket.close();
                System.out.println("Connection closed with the client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String translate(String text, int languageCode) {
        // TextTranslator class handles the translation logic
        TextTranslator translator = new TextTranslator();
        return translator.translateText(text, languageCode);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerJFrame serverFrame = new ServerJFrame();
            serverFrame.setVisible(true);

            Server server = new Server(serverFrame);
            Thread serverThread = new Thread(server::startServer);
            serverThread.start();
        });
    }
}
