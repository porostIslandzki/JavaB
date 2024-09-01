import music.Song;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class SongWrongIndex {
    @BeforeAll
    public static void connectToDatabase() {
        // Symulacja połączenia z bazą danych i dodanie piosenek do "bazy"
        Song.Persistence.addSong(new Song("Artist 1", "Song 1", 45));
        Song.Persistence.addSong(new Song("Artist 2", "Song 2", 60));
        Song.Persistence.addSong(new Song("Artist 3", "Song 3", 84));
        System.out.println("Connected to database and added songs.");
    }

    @AfterAll
    public static void disconnectFromDatabase() {
        // Symulacja rozłączenia z bazą danych
        System.out.println("Disconnected from database.");
    }

    @Test
    public void read_incorrectIndex_resultEmpty() {
        // given
        int incorrectIndex = 5; // Indeks poza zakresem, ponieważ mamy tylko 3 piosenki

        // when
        Optional<Song> result = Song.Persistence.read(incorrectIndex);

        // then
        Assertions.assertTrue(result.isEmpty(), "Song should not be present at an incorrect index.");
    }
}

//Napisz test sprawdzający próbę odczytu piosenki i
// niepoprawnym indeksie. Wydziel łączenie i rozłączanie
// się z bazą do oddzielnych metod i nadaj im odpowiednie
// adnotacje.