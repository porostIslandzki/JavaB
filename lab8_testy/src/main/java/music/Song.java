package music;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

public record Song(String artist, String title, int time) {

    public String getTitle() {
        return title;
    }

    //stwórz publiczną, statyczną klasę Persistence.
    // W tej klasie powinna znaleźć się metoda read,
    // która przyjmuje indeks, a zwraca obiekt Optional<Song>,
    // zapełniony lub nie, w zależności od poprawności indeksu
    // w bazie.
    public static class Persistence {
        // Statyczna lista utworów reprezentująca bazę danych
        private static List<Song> database = new ArrayList<>();

        // Metoda do dodawania utworów do bazy danych (do celów testowych)
        public static void addSong(Song song) {
            database.add(song);
        }

        // Statyczna metoda read, która zwraca Optional<Song>
        public static Optional<Song> read(int index) {
            if (index >= 0 && index < database.size()) {
                return Optional.of(database.get(index));
            } else {
                return Optional.empty();
            }
        }
    }
}
