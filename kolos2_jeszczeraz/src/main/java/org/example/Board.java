package org.example;

import java.io.IOException;
import java.util.Arrays;

public abstract class Board {

    protected Symbol[][] board; // Plansza 3x3, chronione pole, aby było dostępne w klasach dziedziczących

    // Konstruktor do inicjalizacji planszy z pustymi polami
    public Board() {
        board = new Symbol[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(board[i], Symbol.EMPTY);
        }
    }

    // Setter do naniesienia położenia i symbolu
    public void setPlacement(int row, int col, Symbol symbol) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == Symbol.EMPTY) {
                board[row][col] = symbol;
            } else {
                throw new IllegalArgumentException("Pole nie jest puste");
            }
        } else {
            throw new IllegalArgumentException("Niepoprawne współrzędne");
        }
    }

    // Getter do odczytania symbolu z danego pola
    public Symbol getSymbolAt(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col];
        } else {
            throw new IllegalArgumentException("Niepoprawne współrzędne");
        }
    }

    // Abstrakcyjne metody do implementacji w klasach dziedziczących
    public abstract void start() throws IOException;
    public abstract void playGame(Player player1, Player player2);
}
