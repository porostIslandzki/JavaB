package org.example;

import java.io.IOException;

// Klasa reprezentująca abstrakcyjną planszę do gry
public abstract class Board {
    // Plansza o wymiarach 3x3
    Symbol[][] board = new Symbol[3][3];

    // Metoda ustawiająca symbol na planszy w określonych współrzędnych
    public void setPlacement(Symbol symbol, int x, int y) {
        // Sprawdzamy, czy wybrane miejsce na planszy jest puste
        if (board[x][y] == null) {
            board[x][y] = symbol;
        } else {
            System.out.println("Miejsce jest zajęte, wybierz inne pole.");
        }
    }

    // Metoda wyświetlająca aktualny stan planszy
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    System.out.print("- "); // Puste miejsce na planszy
                } else {
                    System.out.print(board[i][j] + " "); // Symbol gracza
                }
            }
            System.out.println();
        }
    }

    public Symbol[][] getBoard() {
        return board;
    }

    // Abstrakcyjna metoda do rozpoczęcia gry
    public abstract void start() throws IOException;

    // Abstrakcyjna metoda do rozgrywania gry
    public abstract void playGame(Player player1, Player player2);
}
