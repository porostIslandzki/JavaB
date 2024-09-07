package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Remote_Board extends Board{
    private ServerSocket serverSocket;
    private Socket player1Socket;
    private Socket player2Socket;
    LocalBoard localBoard;
    

    public Remote_Board() throws IOException {
        serverSocket = new ServerSocket(3001);
    }
    @Override
    public void start() throws IOException {
        //Uruchamianie serwera i oczekiwanie
        // na połączenia od klientów
        System.out.println("Oczekiwanie na gracza 1: ");
        player1Socket = serverSocket.accept();
        System.out.println("Gracz 1 połączony.");

        System.out.println("Oczekiwanie na gracza 2: ");
        player2Socket = serverSocket.accept();
        System.out.println("Gracz 2 połączony.");

    }

    @Override
    public void playGame(Player player1, Player player2) {

    }
}
