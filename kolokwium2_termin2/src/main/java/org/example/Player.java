package org.example;

// Abstrakcyjna klasa reprezentująca gracza
public abstract class Player {
    protected Symbol symbol; // Zmieniono na protected, aby była dostępna w klasach dziedziczących
    protected Board board;   // Zmieniono na protected, aby była dostępna w klasach dziedziczących

    // Konstruktor przyjmujący symbol i planszę
    public Player(Symbol symbol, Board board) {
        this.symbol = symbol; //Każdy gracz ma swój symbol X lub O
        this.board = board; //i wywołuje ruchy na planszy board
    }

    // Abstrakcyjna metoda do wykonania ruchu przez gracza
    public abstract void playTurn();

    // Abstrakcyjna metoda do informowania gracza
    public abstract void inform(String message);
}

//szablon dla gracza