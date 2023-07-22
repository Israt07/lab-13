package demotcpclienttext;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class, ClientFrame, represents the GUI frame for the TCP client application. It displays the server word count
 * and the connection status.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class ClientFrame extends JFrame {

    private JLabel lblServerWordCount;
    private JLabel lblStatusValue;

    public ClientFrame() {
        // Set up the frame properties
        this.setLayout(new BorderLayout());
        this.setTitle("TCP Application: Client Side");
        this.setSize(700, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Initialize the labels for server word count and connection status
        lblServerWordCount = new JLabel("-");
        lblStatusValue = new JLabel("-");

        // Load the GUI components
        loadComponents();
    }

    // Method to update the server word count on the GUI
    public void updateServerWordCount(String wordCount) {
        lblServerWordCount.setText(wordCount);
    }

    // Method to update the connection status on the GUI
    public void updateConnectionStatus(boolean connStatus) {
        String status = connStatus ? "Connection established." : "No connection to server.";
        lblStatusValue.setText(status);
    }

    // Method to update the server date on the GUI (Not used in this class)
    public void updateServerDate(String serverDate) {
        // Update the server date here if needed
    }

    // Method to load and organize the GUI components
    private void loadComponents() {
        Font font = new Font("Serif", Font.PLAIN, 30);

        // Create the top panel to display the connection status
        JPanel northPanel = getConnectionStatusPanel(font);
        this.add(northPanel, BorderLayout.NORTH);

        // Create the center panel to display the server word count
        JPanel centerPanel = getServerWordCountPanel(font);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    // Method to create the panel for displaying the connection status
    private JPanel getConnectionStatusPanel(Font font) {
        JPanel panel = new JPanel();
        JLabel lblConnStatus = new JLabel("Connection status: ");

        lblConnStatus.setFont(font);
        lblStatusValue.setFont(font);
        lblConnStatus.setBackground(Color.WHITE);
        lblConnStatus.setOpaque(true);
        lblStatusValue.setBackground(Color.WHITE);
        lblStatusValue.setOpaque(true);

        panel.add(lblConnStatus);
        panel.add(lblStatusValue);

        return panel;
    }

    // Method to create the panel for displaying the server word count
    private JPanel getServerWordCountPanel(Font font) {
        JPanel panel = new JPanel();
        JLabel lblWordCount = new JLabel("Word Count: ");

        lblWordCount.setFont(font);
        lblServerWordCount.setFont(font);
        lblWordCount.setBackground(Color.WHITE);
        lblWordCount.setOpaque(true);
        lblServerWordCount.setBackground(Color.WHITE);
        lblServerWordCount.setOpaque(true);

        panel.add(lblWordCount);
        panel.add(lblServerWordCount);

        return panel;
    }
}
