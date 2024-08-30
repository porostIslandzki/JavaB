import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaylistTest {

    @Test
    public void testAtSecond() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("Crystal Castles", "Untrust us", 120));  // 0 - 120 seconds
        playlist.add(new Song("Will Wood", "Against the kitchen floor", 180));  // 121 - 300 seconds
        playlist.add(new Song("Linkin Park", "Numb", 240));  // 301 - 540 seconds

        // Test 1: Sprawdzenie, czy po 10 sekundach od początku odtwarzany jest pierwszy utwór
        Song songAt10 = playlist.atSecond(10);
        assertEquals("Untrust us", songAt10.title());

        // Test 2: Sprawdzenie, czy po 150 sekundach od początku odtwarzany jest drugi utwór
        Song songAt150 = playlist.atSecond(150);
        assertEquals("Against the kitchen floor", songAt150.title());

        // Test 3: Sprawdzenie, czy po 400 sekundach od początku odtwarzany jest trzeci utwór
        Song songAt400 = playlist.atSecond(400);
        assertEquals("Numb", songAt400.title());

        // Test 4: Sprawdzenie, czy wyjątek NoSuchElementException jest rzucany,
        // gdy podana liczba sekund przekracza całkowity czas trwania playlisty
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(600));
    }
}
