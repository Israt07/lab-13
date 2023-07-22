package demotcpclienttranslatorgui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * The Client class represents a graphical user interface for 
 * a client-side translator application.
 * It allows the user to select a sentence and 
 * a target language for translation and displays the translated text.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class Client extends JFrame {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private JTextArea outputTextArea;
    private JComboBox<String> languageComboBox;

    public Client() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 4011);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Client Translator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel sentenceLabel = new JLabel("Select a sentence to translate:");
        topPanel.add(sentenceLabel);

        String[] sentences = {"Good morning", "Good afternoon", "Good evening", "How are you?", "Thank you", "Goodbye"};
        for (int i = 0; i < sentences.length; i++) {
            JButton button = new JButton(sentences[i]);
            int finalI = i + 1; // To avoid the lambda using the loop variable directly
            button.addActionListener(e -> translateSentence(finalI));
            topPanel.add(button);
        }

        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JLabel languageLabel = new JLabel("Select a target language:");
        bottomPanel.add(languageLabel);

        String[] languages = {"English", "Bahasa Malaysia", "Arabic", "Korean"};
        languageComboBox = new JComboBox<>(languages);
        bottomPanel.add(languageComboBox);

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        bottomPanel.add(scrollPane);

        add(bottomPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void translateSentence(int choice) {
        int languageCode = languageComboBox.getSelectedIndex() + 1;
        String text = getTextToTranslate(choice);
        writer.println(text);
        writer.println(languageCode);

        try {
            String translatedText = reader.readLine();
            outputTextArea.append("Original Text: " + text + "\n");
            outputTextArea.append("Translated Text: " + translatedText + "\n\n");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client clientGUI = new Client();
            clientGUI.setVisible(true);
        });
    }
}
