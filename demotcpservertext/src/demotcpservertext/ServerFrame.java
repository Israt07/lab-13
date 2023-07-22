package demotcpservertext;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


/**
 * This class, ServerFrame, represents a GUI frame for the TCP server application. It provides a graphical interface
 * to display the server status and the status of client requests.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class ServerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblServerStatus;
    private JTextArea txtRequestStatus;

    public ServerFrame() {
        // Frame setup
        this.setLayout(new BorderLayout());
        this.setTitle("TCP Application: Server Side");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(400, 300));
        this.setLocationRelativeTo(null);

        // Server status label setup
        lblServerStatus = new JLabel("Server status: Offline");
        lblServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblServerStatus.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        // Request status text area setup
        txtRequestStatus = new JTextArea();
        txtRequestStatus.setEditable(false);

        // Top panel to hold the server status label
        JPanel topPanel = new JPanel();
        topPanel.add(lblServerStatus);

        // Adding components to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(txtRequestStatus, BorderLayout.CENTER);
    }

    // Method to update the server status (Online/Offline)
    public void updateServerStatus(boolean flag) {
        String status = flag ? "Server status: Online" : "Server status: Offline";
        lblServerStatus.setText(status);
    }

    // Method to update the request status displayed in the text area
    public void updateRequestStatus(String status) {
        txtRequestStatus.append(status + "\n");
    }
}
