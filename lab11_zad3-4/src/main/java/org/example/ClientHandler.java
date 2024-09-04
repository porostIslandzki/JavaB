package org.example;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final Serwer server;

    public ClientHandler(Socket socket, Serwer server) throws IOException {
        this.socket = socket;
        this.server = server;

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        reader = new BufferedReader(new InputStreamReader(input));

        writer = new PrintWriter(output, true);
    }

    public void send(String message){
        writer.println(message);
    }

    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }
        server.removeHandler(this);
    }

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

