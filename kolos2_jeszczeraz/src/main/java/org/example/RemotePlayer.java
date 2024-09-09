package org.example;

import java.io.*;
import java.net.Socket;

public class RemotePlayer extends Player implements Runnable{

    private final Socket socket;
    private final BufferedReader reader; //odbieranie danych od Serwera
    private final PrintWriter writer; //wysyłanie wiadomości do Serwera


    public RemotePlayer(Board board, String address, int port) throws IOException {
        super(board);
        this.socket = new Socket(address, port); //inicjalizacja połączenia z serwere

        // Pobieramy strumienie wejściowy i wyjściowy z gniazda
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // Tworzymy obiekt BufferedReader do odczytu danych od klienta
        reader = new BufferedReader(new InputStreamReader(input));

        // Tworzymy obiekt PrintWriter do wysyłania danych do klienta
        writer = new PrintWriter(output, true); // Flaga true włącza auto-flush
    }

    //metoda do wysyłania wiadomości do serwera
    //Korzysta z obiektu PrintWriter do wysyłania danych
    //przez połączenie Socket
    public void send(String message){
        writer.println(message);
    }

    @Override
    public void run() { //uruchamia klienta w osobnym wątku,
        //odpowiada za nasłuchiwanie wiadomości od serwera i ich
        //wyświetlanie. W tym celu używa pętli, która ciągle odbiera
        //wiadomości za pomocą BufferedReader
        try {
            String message;
            while ((message = reader.readLine()) != null){
                System.out.println("Serwer: " + message); // Wyświetlanie wiadomości od serwera
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // Zamknięcie połączenia
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    }

    @Override
    public void playTurn() throws IOException {
        //metoda ta musi umożliwiać graczowi przesyłanie ruchów
        //do serwera
        //w tej metodzie
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String move = consoleReader.readLine(); //Gracz wpisuje swój ruch
        send(move); //wysłanie ruchu do serwera

    }

    @Override
    public String inform(String message) {
        //ta metoda wysyła do serwera informacje (np. komunikaty o zakończeniu
        //tury)
        send(message); // Wyślij komunikat do serwera
        return "Wiadomość wysłana do serwera: " + message;
    }
}

//Klasa RemotePlayer powinna posiadać dodatkowo funkcjonalność
//przesyłania i odbierania wiadomości od i do klienta przy
//użyciu gniazd sieciowych.

//Cel: Stworzenie gracza(RemotePlayer), który komunikuje się
//z serwerem (RemoteBoard) za pomocą gniazd sieciowych,
//umożliwiając przesyłanie ruchów w grze i odbieranie
//odpowiedzi