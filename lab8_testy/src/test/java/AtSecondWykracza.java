import music.Playlist;
import music.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtSecondWykracza {
    //podany czas wykracza poza czas trwania listy
    @Test
    public void atSecond_totalSecondsNotOk_throwsException(){
        //given
        Song song = new Song("Artist 1", "Song 1", 13);
        Song song1 = new Song("Artist 2", "Song 2", 16);
        Song song2 = new Song("Artist 3", "Song 3", 17);
        Playlist playlist = new Playlist();
        playlist.add(song);
        playlist.add(song1);
        playlist.add(song2);
        int totalSeconds = 30000;

        //then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(totalSeconds));
    }
}
