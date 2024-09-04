package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serwer {

    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> handlers = new ArrayList<>();
    //serwer powinien przechowywać listę połączonych klientów, aby
    //mógł rozsyłać wiadomości do wszystkich klientów

    //1. Serwer powinien otworzyć gniazdo (ServerSocket)
    // na ustalonym
    //porcie i nasłuchiwać na połączenia od klientów.
    public Serwer() throws IOException {
        serverSocket = new ServerSocket(3000);
    }

    //2.Aby obsłużyć wielu klientów, serwer musi
    //być wielowątkowy. Oznacza to, że dla każdego
    //nowego klienta, który się połączy, serwer powinien uruchomić
    //osobmy wątek
    //Dla każdego klienta serwer tworzy nową instancję
    //klasy odpowiedzialnej za komunikację z tym klientem
    //(ClientHandler)
    //i uruchamia ją w nowym wątku
    public void listen() throws IOException{
        System.out.println("Server started");

        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            ClientHandler handler = new ClientHandler(socket, this);
            Thread thread = new Thread(handler);
            thread.start();
            handlers.add(handler); //za każdym razem,gdy klient się
            //połączy, jego handler powinien zostać dodany do listy
            //gdy się rozłączy, handler powinien zostać usunięty
        }
    }

    // Metoda do usunięcia handlera, gdy klient się rozłączy
    public void removeHandler(ClientHandler handler) {
        handlers.remove(handler);
    }

    //Serwer powinien rozsyłać wiadomości otrzymane od dowolnego
    //klienta do wszystkich pozostałych klientów
    public void broadcast(String message){
        handlers.forEach(handler -> handler.send(message));
    }
}
