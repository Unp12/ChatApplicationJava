package com.GUI.ChatApplication.Version2;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class SimpleChatServerB {
    private ArrayList<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        new SimpleChatServerB().go();
    }

    public void go() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(5004)) { // Try-with-resources for auto-closing
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("New connection established.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            threadPool.shutdown(); // Shutdown the thread pool
        }
    }

    private void broadcast(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public class ClientHandler implements Runnable {
        private BufferedReader reader;
        private Socket socket; // Add socket as a field

        public ClientHandler(Socket socket) {
            this.socket = socket; // Initialize socket
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message);
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
}