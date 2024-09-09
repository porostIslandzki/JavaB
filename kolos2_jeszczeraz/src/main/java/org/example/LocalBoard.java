package org.example;

public class LocalBoard extends Board {

    private boolean endGame = false;

    @Override
    public void start() {
        System.out.println("Gra rozpoczęta!");
    }

    @Override
    public void playGame(Player player1, Player player2) {
        while (!endGame) {
            // Tura gracza 1
            player1.playTurn();
            if (checkWinner() != null) {
                player1.inform(checkWinner());
                player2.inform(checkWinner());
                endGame = true;
                break;
            }

            // Tura gracza 2
            player2.playTurn();
            if (checkWinner() != null) {
                player1.inform(checkWinner());
                player2.inform(checkWinner());
                endGame = true;
                break;
            }
        }
    }

    // Sprawdzanie zwycięzcy lub remisu
    public String checkWinner() {
        // Sprawdzanie wierszy, kolumn i przekątnych
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Symbol.EMPTY) {
                return "Zwycięzca: " + board[i][0];
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Symbol.EMPTY) {
                return "Zwycięzca: " + board[0][i];
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Symbol.EMPTY) {
            return "Zwycięzca: " + board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != Symbol.EMPTY) {
            return "Zwycięzca: " + board[0][2];
        }

        // Sprawdzenie, czy jest remis
        boolean isBoardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Symbol.EMPTY) {
                    isBoardFull = false;
                    break;
                }
            }
        }

        if (isBoardFull) {
            return "Remis!";
        }

        return null;
    }
}
