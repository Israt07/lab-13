package demotcpservertranslatorgui;

import javax.swing.*;

import java.awt.*;

/**
 * This class, ServerJFrame, represents the graphical user interface (GUI) for the TCP server.
 * It displays the server status and the request status (translated text) in a JFrame.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */

public class ServerJFrame extends JFrame {
    private JLabel lblServerStatus;
    private JTextArea txtRequestStatus;

    public ServerJFrame() {
        setTitle("Server");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize component
        this.lblServerStatus = new JLabel("Server started and listening on port 4011");
        this.txtRequestStatus = new JTextArea(20, 70);
        txtRequestStatus.setEditable(false);

        loadComponent();
    }

    private void loadComponent() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Server status panel
        JPanel serverStatusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        serverStatusPanel.add(lblServerStatus);

        // Request status panel
        JPanel requestStatusPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(txtRequestStatus);
        requestStatusPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(serverStatusPanel, BorderLayout.NORTH);
        mainPanel.add(requestStatusPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void updateServerStatus(boolean flag) {
        String status = flag ? "Server status: Received connection." : "Server status: Waiting for connection.";
        lblServerStatus.setText(status);
    }

    public void updateRequestStatus(String status) {
        String currentText = txtRequestStatus.getText();
        txtRequestStatus.setEditable(true);
        txtRequestStatus.setText("> " + status + "\n" + currentText);
        txtRequestStatus.setEditable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServerJFrame serverFrame = new ServerJFrame();
            serverFrame.setVisible(true);
            JOptionPane.showMessageDialog(serverFrame, "Server started and listening on port 4011", "Server Information", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
