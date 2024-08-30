import music.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AtSecondUjemny {
    @Test
    public void atSecond_totalSecondsLessThan0_resultFalse(){
        //given
        int totalSeconds = -3;
        Playlist playlist = new Playlist();

        //then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> playlist.atSecond(totalSeconds));
    }
}
