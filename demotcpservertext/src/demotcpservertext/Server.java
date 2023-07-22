package demotcpservertext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class, Server, represents a TCP server application that listens for client connections, receives text from clients,
 * processes the text to count the number of words, and sends the word count back to the clients.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Server {
    public static void main(String[] args) {
        try {
            // Create an instance of the server frame
            ServerFrame serverFrame = new ServerFrame();
            serverFrame.setVisible(true);

            // Define the port number for the server to listen on
            int portNo = 4228;

            // Create a ServerSocket to bind and listen to the specified port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // Update the server status and show the port it's listening on
            serverFrame.updateServerStatus(true);
            serverFrame.updateRequestStatus("Server started and listening on port " + portNo);

            // The server needs to be alive forever to keep accepting client connections
            while (true) {
                // Accept a client connection request
                Socket clientSocket = serverSocket.accept();

                // Update the server frame with the accepted client connection
                serverFrame.updateRequestStatus("Accepted connection from client: " + clientSocket.getInetAddress().getHostAddress());

                // Read the text sent by the client
                String text = readText(clientSocket);

                // Process the text to count the number of words
                TextProcessor textProcessor = new TextProcessor();
                int wordCount = textProcessor.countWords(text);

                // Send the word count back to the client
                sendWordCount(clientSocket, wordCount);
                serverFrame.updateRequestStatus("Word count sent to the client: " + wordCount);

                // Close the connection with the client
                clientSocket.close();
                serverFrame.updateRequestStatus("Connection closed with the client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read the text sent by the client
    private static String readText(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return reader.readLine();
    }

    // Method to send the word count back to the client
    private static void sendWordCount(Socket socket, int wordCount) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(wordCount);
    }
}
