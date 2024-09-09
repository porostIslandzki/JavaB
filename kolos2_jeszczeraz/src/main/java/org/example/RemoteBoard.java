package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteBoard extends Board{

    private ServerSocket serverSocket;
    private Socket player1Socket;
    private Socket player2Socket;
    private PrintWriter out1;
    private PrintWriter out2;
    private BufferedReader in1;
    private BufferedReader in2;

    public RemoteBoard() throws IOException {
        serverSocket = new ServerSocket(4000);
    }

    @Override
    public void start() throws IOException {
        //po wywołaniu poczeka na połączenie jednego klienta, utworzy
        //obiekt zdalnego gracza, a następnie wykona analogiczne kroki
        //dla drugiego z graczy
        System.out.println("Server started");

        while (true){
            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected");
            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected");

            // Inicjalizacja strumieni komunikacji
            out1 = new PrintWriter(player1Socket.getOutputStream(), true);
            out2 = new PrintWriter(player2Socket.getOutputStream(), true);
            in1 = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            in2 = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));

            out1.println("Witaj Graczu 1!");
            out2.println("Witaj Graczu 2!");
        }

    }

    @Override
    public void playGame(Player player1, Player player2) {
        try {
            while (true) {
                // Ruch gracza 1
                String move1 = in1.readLine();
                System.out.println("Gracz 1 ruch: " + move1);
                out2.println("Ruch Gracza 1: " + move1);

                // Ruch gracza 2
                String move2 = in2.readLine();
                System.out.println("Gracz 2 ruch: " + move2);
                out1.println("Ruch Gracza 2: " + move2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//RemoteBoard powinna posiadać dodatkowo funkcjonalność serwera
//wykorzystując ServerSocket