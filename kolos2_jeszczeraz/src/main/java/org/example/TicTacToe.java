package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class TicTacToe {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Podaj tryb uruchomienia: hotseats, server, client, host");
            return;
        }

        String mode = args[0];

        switch (mode.toLowerCase()) {
            case "hotseats":
                runHotSeatsMode();
                break;

            case "server":
                runServerMode();
                break;

            case "client":
                runClientMode();
                break;

            case "host":
                runHostMode();
                break;

            default:
                System.out.println("Nieznany tryb. Użyj: hotseats, server, client, host");
        }
    }

    // Tryb Hotseats - dwaj gracze lokalni
    private static void runHotSeatsMode() throws IOException {
        System.out.println("Tryb hotseats - gra między dwoma graczami na jednej maszynie");
        Board board = new LocalBoard();
        Player player1 = new HumanPlayer(board, new java.util.Scanner(System.in));
        Player player2 = new HumanPlayer(board, new java.util.Scanner(System.in));

        player1.setSymbol(1); // Gracz 1 ma X
        player2.setSymbol(2); // Gracz 2 ma O

        board.start();
        board.playGame(player1, player2);
    }

    // Tryb server - uruchamia tylko serwer
    private static void runServerMode() {
        try {
            System.out.println("Uruchamiam serwer...");
            RemoteBoard serverBoard = new RemoteBoard();
            serverBoard.start();
            System.out.println("Serwer uruchomiony. Oczekuję na graczy...");
        } catch (IOException e) {
            System.out.println("Błąd podczas uruchamiania serwera: " + e.getMessage());
        }
    }

    // Tryb client - uruchamia klienta
    private static void runClientMode() {
        try {
            System.out.println("Łączenie z serwerem...");
            GameClient client = new GameClient("localhost", 4000); // Adres IP serwera, port 4000
        } catch (Exception e) {
            System.out.println("Błąd podczas łączenia z serwerem: " + e.getMessage());
        }
    }

    // Tryb host - uruchamia serwer i klienta
    private static void runHostMode() {
        try {
            System.out.println("Uruchamiam serwer...");
            RemoteBoard serverBoard = new RemoteBoard(); // Uruchamia serwer
            new Thread(() -> {
                try {
                    serverBoard.start(); // Serwer oczekuje na drugiego gracza
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start(); // Uruchom serwer w osobnym wątku

            System.out.println("Serwer uruchomiony. Tworzenie klienta lokalnego...");

            // Uruchom lokalnego klienta
            new Thread(() -> {
                try {
                    GameClient client = new GameClient("localhost", 4000);
                } catch (Exception e) {
                    System.out.println("Błąd przy uruchamianiu klienta: " + e.getMessage());
                }
            }).start();

        } catch (IOException e) {
            System.out.println("Błąd podczas uruchamiania hosta: " + e.getMessage());
        }
    }
}
