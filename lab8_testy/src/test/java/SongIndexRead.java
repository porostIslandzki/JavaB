import music.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongIndexRead {
    //Napisz test sprawdzający odczyt z bazy danych
    // piosenki o poprawnym indeksie.
    @Test
    public void read_correctIndex_resultTrue(){
        //given
        int id = 2;
        Song.Persistence.addSong(new Song("Artist 1", "Song 1", 45));
        Song.Persistence.addSong(new Song("Artist 2", "Song 2", 60));
        Song.Persistence.addSong(new Song("Artist 3", "Song 3", 84));

        //when
        Optional<Song> result = Song.Persistence.read(id);

        //then
        Assertions.assertTrue(result.isPresent(), "Song should be present at the correct index");
        Assertions.assertEquals("Song 3", result.get().getTitle(), "The song title should match the expected value.");
    }

}
