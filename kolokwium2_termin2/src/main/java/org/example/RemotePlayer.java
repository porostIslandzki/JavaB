package org.example;

import java.io.*;
import java.net.Socket;

public class RemotePlayer extends Player{

    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public RemotePlayer(Symbol symbol, Board board,
                        Socket socket) throws IOException {
        super(symbol, board);
        this.socket = socket;

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

    // Metoda do odbierania wiadomości od klienta
    public String receive() throws IOException {
        return reader.readLine();
    }

    // Metoda do zamknięcia połączenia
    private void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing socket: " + e.getMessage());
        }
    }

    @Override
    public void playTurn() {

    }

    @Override
    public void inform(String message) {

    }
}
