import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsSame {
    //Napisz jest sprawdzający, czy po dodaniu jednego
    // utworu, jest w nim ten sam utwór.
    @Test
    public void isOne(){
        Playlist playlist = new Playlist();
        Song song = new Song("Crystal castles", "Char", 240);
        playlist.add(song);
        assertTrue(playlist.contains(song), "Playlist should contain the added song");
    }
}
