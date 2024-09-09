package org.example;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private final Scanner inputScanner;
    private int positionX;
    private int positionY;

    public HumanPlayer(Board board, Scanner inputScanner) {
        super(board); // Przypisanie planszy w konstruktorze
        this.inputScanner = inputScanner;
    }

    // Wyświetlanie planszy
    private void showBoard() {
        System.out.println("Aktualny stan planszy: ");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(board.getSymbolAt(i, j) + " ");
            }
            System.out.println();
        }
    }

    // Pobieranie współrzędnych od gracza
    private void listenInput() {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Podaj współrzędne (dwie liczby od 0 do 2):");

                System.out.print("Pozycja X: ");
                positionX = Integer.parseInt(inputScanner.nextLine());

                System.out.print("Pozycja Y: ");
                positionY = Integer.parseInt(inputScanner.nextLine());

                // Sprawdzenie, czy współrzędne są w granicach planszy
                if (positionX >= 0 && positionX < 3 && positionY >= 0 && positionY < 3) {
                    // Sprawdzenie, czy pole jest puste
                    if (board.getSymbolAt(positionX, positionY) == Symbol.EMPTY) {
                        validInput = true;
                    } else {
                        System.out.println("Pole jest już zajęte, wybierz inne.");
                    }
                } else {
                    System.out.println("Niepoprawne współrzędne. Wprowadź liczby od 0 do 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawny format");
            }
        }
    }

    @Override
    public void playTurn() {
        listenInput(); // Pobranie współrzędnych
        board.setPlacement(positionX, positionY, symbol); // Ustawienie symbolu na planszy
        showBoard(); // Wyświetlenie aktualnego stanu planszy
        inform("Ruch gracza " + symbol); // Informacja po każdym ruchu
    }

    @Override
    public String inform(String message) {
        System.out.println(message);
        return message;
    }
}
