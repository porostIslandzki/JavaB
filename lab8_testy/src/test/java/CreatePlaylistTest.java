import music.ListenerAccount;
import music.NotEnoughCreditsException;
import music.Playlist;
import music.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePlaylistTest {

    private ListenerAccount listenerAccount;

    @BeforeEach
    public void setUp() throws SQLException {
        ListenerAccount.Persistence.init();
        listenerAccount = new ListenerAccount(1, "TestUser");

        // Dodaj kredyty do konta
        ListenerAccount.Persistence.addCredits(listenerAccount.getId(), 10);

        // Dodaj piosenki do bazy danych
        Song.Persistence.addSong(new Song("Artist1", "Song1", 180));
        Song.Persistence.addSong(new Song("Artist2", "Song2", 200));
        Song.Persistence.addSong(new Song("Artist3", "Song3", 240));
    }

    @Test
    public void testCreatePlaylist_correctPlaylist_resultTrue() throws SQLException, NotEnoughCreditsException {
        //given
        List<Integer> songIds = Arrays.asList(0, 1, 2);

        //when
        Playlist playlist = listenerAccount.createPlaylist(songIds);

        // Predefiniowana playlista do porównania
        Playlist expectedPlaylist = new Playlist();
        expectedPlaylist.add(new Song("Artist1", "Song1", 180));
        expectedPlaylist.add(new Song("Artist2", "Song2", 200));
        expectedPlaylist.add(new Song("Artist3", "Song3", 240));

        //then
        assertEquals(expectedPlaylist, playlist, "The created playlist should match the expected playlist.");
    }
}