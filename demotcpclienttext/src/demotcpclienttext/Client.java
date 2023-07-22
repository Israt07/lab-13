package demotcpclienttext;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class, Client, represents a TCP client application that sends text to a server and receives the word count
 * from the server.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Client {
    public static void main(String[] args) {
        try {
            // Create an instance of the ClientFrame to display GUI for the client
            ClientFrame clientFrame = new ClientFrame();
            clientFrame.setVisible(true);

            // Connect to the server at localhost on port 4228
            Socket socket = new Socket(InetAddress.getLocalHost(), 4228);

            // Update the connection status on the GUI
            clientFrame.updateConnectionStatus(socket.isConnected());

            // The text to be sent to the server for word count
            String text = "My name is israt.";

            // Send the text to the server
            sendText(socket, text);

            // Receive the word count from the server
            int wordCount = receiveWordCount(socket);

            // Update the GUI with the received word count
            clientFrame.updateServerWordCount(String.valueOf(wordCount));

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to send the text to the server
    private static void sendText(Socket socket, String text) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(text);
    }

    // Helper method to receive the word count from the server
    private static int receiveWordCount(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return Integer.parseInt(reader.readLine());
    }
}
