/* import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class CSVTest {

    // Źródło danych z pliku CSV
    static Stream<String[]> readCsv() throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/game_cases.csv"))) {
            List<String[]> data = reader.readAll();
            return data.stream();
        }
    }

    // Parametryzowany test korzystający z danych z CSV
    @ParameterizedTest
    @MethodSource("readCsv")
    @DisplayName("Testowanie warunków zakończenia gry na podstawie CSV")
    void testGameConditions(String[] gameData) {
        // Oczekiwany wynik w ostatniej kolumnie
        String expectedResult = gameData[9];

        // Tutaj należy zaimplementować logikę, która testuje stan gry, np.:
        assertEquals("expected_value", expectedResult);  // Przykład prostego testu
    }
}
*/