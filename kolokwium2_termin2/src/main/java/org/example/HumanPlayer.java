package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// Klasa reprezentująca gracza typu człowiek
public class HumanPlayer extends Player {
    //dziedziczy po klasie player, która zawiera ogóły,
    //ale tutaj to człowiek wykonuje ruch

    // Współrzędne ruchu gracza
    int placementX;
    int placementY;

    // Lista, która przechowuje współrzędne wybrane przez gracza
    ArrayList<Integer> placements = new ArrayList<>();

    // Obiekt Scanner do odczytywania wejścia od użytkownika
    Scanner in = new Scanner(System.in);

    // Konstruktor klasy HumanPlayer
    public HumanPlayer(Symbol symbol, Board board) {
        super(symbol, board); // Wywołanie konstruktora z klasy bazowej Player
    }

    // Metoda odczytująca dane od użytkownika
    public ArrayList<Integer> listenInput() { //człowiek wykonuje ruchy wprowadzając współrzędne na planszy
        // Pobieranie współrzędnych od gracza
        System.out.println("Podaj współrzędną X (0-2): ");
        this.placementX = in.nextInt();
        System.out.println("Podaj współrzędną Y (0-2): ");
        this.placementY = in.nextInt();

        // Sprawdzanie, czy współrzędne mieszczą się w zakresie planszy
        if (placementX >= 3 || placementY >= 3 || placementX < 0 || placementY < 0) {
            System.out.println("Podaj poprawne parametry (0-2).");
        } else {
            placements.add(placementX);
            placements.add(placementY);
        }
        return placements;
    }

    // Metoda wyświetlająca aktualny stan planszy
    public void showBoard() {
        for (int i = 0; i < board.getBoard().length; i++) {  // Iterujemy przez wiersze planszy
            for (int j = 0; j < board.getBoard()[i].length; j++) {  // Iterujemy przez kolumny
                // Jeśli pole jest puste (null), wyświetlamy "-"
                if (board.getBoard()[i][j] == null) {
                    System.out.print("- ");
                } else {
                    // Wyświetlamy symbol znajdujący się na planszy
                    System.out.print(board.getBoard()[i][j] + " ");
                }
            }
            System.out.println();  // Nowa linia po każdym wierszu
        }
    }

    // Implementacja abstrakcyjnej metody playTurn - wykonanie ruchu przez gracza
    @Override
    public void playTurn() { //metoda pyta gracza o jego ruch
        ArrayList<Integer> input = listenInput(); //wywołuje metode z planszy, aby ruch zarejestrowac
        if (input.size() == 2) {
            board.setPlacement(this.symbol, input.get(0), input.get(1)); // Przypisanie symbolu gracza na planszy
        } //plansza aktualizuje stan
    }

    // Implementacja abstrakcyjnej metody inform - informowanie gracza o stanie gry
    @Override
    public void inform(String message) {
        System.out.println(message); // Wyświetla podaną wiadomość
    }
}
