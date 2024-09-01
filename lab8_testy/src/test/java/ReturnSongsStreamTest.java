import music.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReturnSongsStreamTest {

    @ParameterizedTest
    @MethodSource("provideSongIdsForIndexStream")
    public void indexStream_correctStream_resultCorrect(List<Integer> songIds, List<Song> expectedSongs) throws SQLException {
        // Zakładając, że ListenerAccount.Persistence ma metodę indexStream
        Song.Persistence persistence = new Song.Persistence();
        Stream<Song> songStream = persistence.indexStream(songIds);

        // Konwertujemy strumień do listy
        List<Song> resultSongs = songStream.toList();

        // Porównujemy oczekiwaną listę piosenek z wynikiem
        Assertions.assertEquals(expectedSongs, resultSongs, "The stream did not return the expected songs.");
    }

    // Metoda dostarczająca dane testowe
    static Stream<Object[]> provideSongIdsForIndexStream() {
        // Zakładając, że mamy predefiniowane piosenki w bazie danych Song.Persistence
        Song song1 = new Song("Artist1", "Title1", 180);
        Song song2 = new Song("Artist2", "Title2", 240);
        Song song3 = new Song("Artist3", "Title3", 200);

        // Dodajemy piosenki do "bazy danych"
        Song.Persistence.addSong(song1);
        Song.Persistence.addSong(song2);
        Song.Persistence.addSong(song3);

        return Stream.of(
                new Object[]{Arrays.asList(0, 1), Arrays.asList(song1, song2)},  // Test dla identyfikatorów 0, 1
                new Object[]{Arrays.asList(1, 2), Arrays.asList(song2, song3)},  // Test dla identyfikatorów 1, 2
                new Object[]{Arrays.asList(0, 2), Arrays.asList(song1, song3)}   // Test dla identyfikatorów 0, 2
        );
    }
}
