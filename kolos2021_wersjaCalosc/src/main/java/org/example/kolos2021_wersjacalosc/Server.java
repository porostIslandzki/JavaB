package org.example.kolos2021_wersjacalosc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private ServerSocket serverSocket;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    //do obsługiwania każdego klienta w osobnym wątku.
    private final List<ClientHandler> clients = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    //Metoda startująca serwer
    public void start() {
        try{
            //Nasłuchuje na porcie
            serverSocket = new ServerSocket(port);
            System.out.println("Server started, waiting for connections...");

            while (true){
                //Akceptuje połączenia od klientów
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Tworzenie i uruchamianie wątku dla każdego nowego klienta
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                executorService.submit(clientHandler);

                // Dodanie klienta do listy
                clients.add(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Błąd przy uruchamianiu serwera: " + e.getMessage());
        }
    }

    // Metoda do rozgłaszania wiadomości do wszystkich klientów (np. rysowania odcinków)
    public synchronized void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    // Metoda do usuwania klientów z listy po rozłączeniu
    public synchronized void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("Klient odłączony.");
    }

    public static void main(String[] args) {
        int port = 3000; // Port na którym serwer nasłuchuje
        Server server = new Server(port);
        server.start(); // Uruchomienie serwera
    }
}
