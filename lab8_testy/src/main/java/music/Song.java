package music;

import java.util.Optional;
import java.sql.SQLException;

public record Song(String artist, String title, int time) {

    public static class Persistence {
        public static Optional<Song> read(int id) throws SQLException {
            // Logika odczytu piosenki z bazy danych
            // Zastąp tym prawdziwą logiką odczytu
            if (id == 1) { // Przykładowy warunek, aby zwrócić piosenkę
                return Optional.of(new Song("Artist Name", "Song Title", 300));
            } else {
                return Optional.empty();
            }
        }
    }
}
