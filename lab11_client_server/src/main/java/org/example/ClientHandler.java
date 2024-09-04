package org.example;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable { //Runnable oznacza,
    //że może być uruchamiana w osobnym wątku, aby obsługiwać
    //klientów w aplikacji wielowątkowej.

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final Serwer server;

    // Konstruktor klasy ClientHandler
    public ClientHandler(Socket socket, Serwer server) throws IOException {
        this.socket = socket;
        this.server = server; //przypisanie referencji do serwera

        // Pobieramy strumienie wejściowy i wyjściowy z gniazda
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // Tworzymy obiekt BufferedReader do odczytu danych od klienta
        reader = new BufferedReader(new InputStreamReader(input));

        // Tworzymy obiekt PrintWriter do wysyłania danych do klienta
        writer = new PrintWriter(output, true); // Flaga true włącza auto-flush
    }

    //metoda do wysyłania wiadomości do klienta
    public void send(String message){
        writer.println(message);
    }

    // Metoda do zamknięcia połączenia
    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }
        server.removeHandler(this);
    }

    // Implementacja metody run() z interfejsu Runnable
    @Override
    public void run() {
        String message;
        try {
            send("Welcome to the chat!");
            //odczytanie wiadomości od klienta
            while ((message = reader.readLine()) != null){
                server.broadcast(message); //przekazanie referencji do nadawcy
            }
        } catch (IOException e){
            throw  new RuntimeException(e);
        } finally {
            close(); //zamknięcie połączenia po zakończeniu komunikacji
            }
        }
}

//Napisz serwer czatu sieciowego. Serwer powinien pozwolić
// dołączyć dowolnej liczbie użytkowników. Serwer powinien
// oczekiwać na wiadomość od dowolnego użytkownika i rozsyłać
// tę wiadomość wszystkim użytkownikom.