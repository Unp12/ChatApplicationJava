package com.GUI.ChatApplication.Version2;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.ConnectException; // Import for handling connection errors

public class SimpleChatClientB {
    private JTextArea incoming;
    private JTextField outgoing;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket socket; // Add socket as a fielda

    public void go() {
        JFrame frame = new JFrame("Chat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 30);
        incoming.setLineWrap(true);
        incoming.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(incoming);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        mainPanel.add(scrollPane);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setUpNetworking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
    }

    private void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1", 5004); // Initialize socket
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (ConnectException ce) { // Handle connection exception
            incoming.append("Server not running. Please start the server.\n");
            ce.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        if (writer != null) { // Check if writer is initialized
            writer.println(outgoing.getText());
            writer.flush();
            outgoing.setText("");
            outgoing.requestFocus();
        } else {
            incoming.append("Cannot send message. Server connection not established.\n");
        }
    }

    public class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    String finalMessage = message;
                    SwingUtilities.invokeLater(() -> incoming.append(finalMessage + "\n")); // Update GUI on EDT
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally { // Close resources in a finally block
                try {
                    if (reader != null) reader.close();
                    if (socket != null) socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new SimpleChatClientB().go();
    }
}