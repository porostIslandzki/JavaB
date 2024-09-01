import music.Playlist;
import music.Song;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongsCSVTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/songs.csv", numLinesToSkip = 1)
    void csvIndex(int id, String artist, String title, int length) {
        // Tworzymy playlistę i dodajemy piosenki na podstawie pliku CSV
        Playlist playlist = new Playlist();
        playlist.add(new Song(artist, title, length)); // Dodajemy piosenkę z CSV

        // Sprawdzamy czy piosenka na odpowiednim indeksie w playliście ma oczekiwane wartości
        Song song = playlist.get(id - 1);  // `id - 1`, ponieważ indeksy w CSV zaczynają się od 1, a w liście od 0

        assertEquals(artist, song.artist(), "Artist does not match");
        assertEquals(title, song.title(), "Title does not match");
        assertEquals(length, song.time(), "Length does not match");
    }
}
