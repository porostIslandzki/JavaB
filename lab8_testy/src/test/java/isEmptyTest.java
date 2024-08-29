import music.Playlist;
import org.junit.jupiter.api.Test;

import java.lang.foreign.PaddingLayout;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class isEmptyTest {
    //Napisz test sprawdzający
    //czy nowo utworzona playlista jest
    //pusta
    @Test
    public void testNewPlaylistIsEmpty(){
        Playlist playlist = new Playlist();
        //Sprawdzamy, czy jest pusta
        assertTrue(playlist.isEmpty(), "Nowo utworzona playlista jest pusta. ");
    }

}
