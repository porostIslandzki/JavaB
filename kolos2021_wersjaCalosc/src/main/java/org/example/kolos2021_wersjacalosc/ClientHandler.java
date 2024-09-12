package org.example.kolos2021_wersjacalosc;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String currentColor = "#000000"; // Domyślny kolor to czarny

    // Konstruktor przyjmuje socket klienta i referencję do serwera
    public ClientHandler(Socket socket, Server server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            // Inicjalizacja strumieni do komunikacji z klientem
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;

            // Pętla do odbierania wiadomości od klienta
            while ((message = in.readLine()) != null) {
                if (message.matches("[0-9A-Fa-f]{6}")) {
                    // Jeżeli wiadomość to kolor, zapisujemy go
                    currentColor = "#" + message;
                } else {
                    // Jeżeli wiadomość to współrzędne odcinka, rozgłaszamy ją wraz z kolorem
                    server.broadcast(currentColor + " " + message);
                }
            }

        } catch (IOException e) {
            System.err.println("Błąd komunikacji z klientem: " + e.getMessage());
        } finally {
            close(); // Zamknięcie połączenia po zakończeniu komunikacji
        }
    }

    // Metoda do wysyłania wiadomości do klienta
    public void sendMessage(String message) {
        out.println(message);
    }

    // Zamknięcie połączenia
    private void close() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            server.removeClient(this); // Usunięcie klienta z serwera
        } catch (IOException e) {
            System.err.println("Błąd przy zamykaniu połączenia: " + e.getMessage());
        }
    }
}


//Obsługa indywidualnego klienta po połączeniu z serwerem
//Przechowywanie informacji o gnieździe
//strumienie odczytu BufferedReader i zapisu PrintWriter
//wiadomość przekazana do HelloApplication