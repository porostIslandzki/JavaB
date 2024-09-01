package music;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;
import java.util.stream.Stream;

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

        //metoda zwracająca stumień na podstawie listy identyfikatorów
        //piosenek
        public static Stream<Song> indexStream(List<Integer> songIds) throws SQLException {
            return songIds.stream()
                    .map(Song.Persistence::read)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        }
        //songIds.stream(): Tworzymy strumień z listy identyfikatorów piosenek.
        //.map(Song.Persistence::read): Dla każdego identyfikatora piosenki próbujemy odczytać piosenkę z bazy danych za pomocą metody read, która zwraca Optional<Song>.
        //.filter(Optional::isPresent): Przechodzimy dalej tylko z tymi piosenkami, które rzeczywiście są obecne w bazie danych (czyli Optional nie jest pusty).
        //.map(Optional::get): Pobieramy piosenkę z Optional, co zwraca obiekt Song.

    }
}
