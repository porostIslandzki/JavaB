package org.example;

// Konkretny typ planszy, lokalna plansza do gry
public class LocalBoard extends Board {

    // Implementacja abstrakcyjnej metody start - rozpoczęcie gry
    @Override
    public void start() {
        System.out.println("Gra rozpoczęta!");
    }

    // Implementacja abstrakcyjnej metody playGame - rozgrywka między dwoma graczami
    @Override
    public void playGame(Player player1, Player player2) {
        start();
        boolean gameRunning = true;
        int turn = 0;

        while (gameRunning) {
            // Sprawdzamy, który gracz ma teraz wykonać ruch
            if (turn % 2 == 0) {
                player1.playTurn(); // Ruch gracza 1
                displayBoard(); // Wyświetlenie planszy po ruchu
                if (checkVictory(player1.symbol)) {
                    System.out.println("Wygrał gracz 1!");
                    gameRunning = false; // Zatrzymanie gry po wygranej
                    break;
                }
            } else {
                player2.playTurn(); // Ruch gracza 2
                displayBoard(); // Wyświetlenie planszy po ruchu
                if (checkVictory(player2.symbol)) {
                    System.out.println("Wygrał gracz 2!");
                    gameRunning = false; // Zatrzymanie gry po wygranej
                    break;
                }
            }
            turn++;

            // Sprawdzenie, czy doszło do remisu (jeśli plansza jest pełna)
            if (turn == 9) {  // Bo na planszy 3x3 mamy maksymalnie 9 ruchów
                System.out.println("Remis!");
                gameRunning = false; // Zatrzymanie gry po remisie
            }
        }
    }

    private Symbol[][] board = new Symbol[3][3];  // Plansza 3x3

    // Metoda do sprawdzania warunków zwycięstwa
    public boolean checkVictory(Symbol symbol) {
        // Sprawdzanie wierszy
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;  // Zwycięstwo w wierszu
            }
        }

        // Sprawdzanie kolumn
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;  // Zwycięstwo w kolumnie
            }
        }

        // Sprawdzanie przekątnych
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;  // Zwycięstwo na głównej przekątnej
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;  // Zwycięstwo na drugiej przekątnej
        }

        // Jeśli żaden warunek zwycięstwa nie jest spełniony
        return false;
    }

    // Metoda wyświetlająca planszę po każdym ruchu
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
