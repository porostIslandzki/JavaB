import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeOne {
    //Napisz test sprawdzający, czy po dodaniu
    // jednego utworu playlista ma rozmiar 1.
    @Test
    public void isOne(){
        Playlist playlist = new Playlist();
        playlist.add(new Song("Crystal castles", "Char", 240));
        int size = playlist.size();
        assertEquals(1, size);
    }

}
