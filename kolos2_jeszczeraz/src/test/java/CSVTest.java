import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVTest {

    @Test
    void testGameEndCondition() throws IOException, CsvException {
        // Otwórz plik CSV
        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/game-end-cases.csv"))) {
            List<String[]> lines = reader.readAll(); // Odczytaj wszystkie wiersze na raz

            lines.remove(0); // Pomiń nagłówek (pierwszy wiersz)

            for (String[] line : lines) {
                // Odczytaj dane planszy
                String p1 = line[0], p2 = line[1], p3 = line[2];
                String p4 = line[3], p5 = line[4], p6 = line[5];
                String p7 = line[6], p8 = line[7], p9 = line[8];
                String expectedResult = line[9]; // Oczekiwany wynik gry

                // Wywołaj metodę do sprawdzenia wyniku gry
                String actualResult = checkGameEnd(p1, p2, p3, p4, p5, p6, p7, p8, p9);

                // Porównaj wynik rzeczywisty z oczekiwanym
                assertEquals(expectedResult, actualResult, "Stan planszy nie spełnia oczekiwań!");
            }
        }
    }

    // Przykładowa metoda do sprawdzania warunków zakończenia gry
    private String checkGameEnd(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        // Twoja logika gry, która analizuje pola planszy (p1 - p9) i zwraca wynik

        // Przykład (uproszczona logika):
        if (p1.equals("X") && p2.equals("X") && p3.equals("X")) return "X wins";
        if (p4.equals("X") && p5.equals("X") && p6.equals("X")) return "X wins";
        if (p7.equals("O") && p8.equals("O") && p9.equals("O")) return "O wins";

        // Jeśli plansza jest pełna, zwracamy "draw"
        if (!p1.equals(" ") && !p2.equals(" ") && !p3.equals(" ") &&
                !p4.equals(" ") && !p5.equals(" ") && !p6.equals(" ") &&
                !p7.equals(" ") && !p8.equals(" ") && !p9.equals(" ")) {
            return "draw";
        }

        return "game continues";
    }
}

//Napisz sparametryzowany plikiem CSV test,
//sprawdzający co najmniej trzy przypadki
//warunku zakończenia gry
//W pliku powinien być podany stan planszy
//oraz jedna z sytuacji: gra trwa, wygrał X, wygrało O, remis