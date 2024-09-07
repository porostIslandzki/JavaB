package org.example;

//hotseats trzeba dodać w edit configurations

public class TicTacToe {
    public static void main(String[] args) {
        // Sprawdzenie, czy został przekazany argument "hotseats"
        if (args.length > 0 && args[0].equals("hotseats")) {
            // Tworzenie planszy do gry
            LocalBoard board = new LocalBoard();

            // Tworzenie dwóch graczy: Gracz 1 to 'X', Gracz 2 to 'O'
            Player player1 = new HumanPlayer(Symbol.X, board);
            Player player2 = new HumanPlayer(Symbol.O, board);

            // Rozpoczęcie gry pomiędzy graczami
            board.playGame(player1, player2);
        } else {
            System.out.println("Podaj 'hotseats' jako pierwszy argument, aby rozpocząć grę.");
        }
    }
}
