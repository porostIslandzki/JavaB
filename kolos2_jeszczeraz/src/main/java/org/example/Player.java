package org.example;

import java.io.IOException;

public abstract class Player {
    protected Board board; // Referencja do planszy
    protected Symbol symbol; // Symbol przypisany graczowi (X lub O)

    public Player(Board board) {
        this.board = board;
    }

    // Metoda do ustawiania symbolu (X lub O) na podstawie numeru gracza
    public void setSymbol(int playerNumber) {
        if (playerNumber == 1) {
            this.symbol = Symbol.X; // Gracz 1 dostaje X
        } else if (playerNumber == 2) {
            this.symbol = Symbol.O; // Gracz 2 dostaje O
        } else {
            throw new IllegalArgumentException("Niepoprawny numer gracza");
        }
    }

    // Abstrakcyjne metody
    public abstract void playTurn() throws IOException;
    public abstract String inform(String message);
}


//Napisz klasę abstrakcyjną Player zawierającą przypisany graczowi symbol
//oraz referencję na obiekt Board na którym odbywa się gra. Niech klasa
//Player posiada abstrakcyjną metodę playTurn() oraz inform() przyjmującą
//String
