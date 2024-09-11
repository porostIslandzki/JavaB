package org.example.kolos2021;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private ServerSocket serverSocket;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started, waiting for connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Czekanie na klienta
                System.out.println("Client connected");
                executorService.submit(new ClientReceiver(clientSocket)); // Obsługa klienta w osobnym wątku
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(3000); // Serwer na porcie 3000
        server.start();
    }
}
