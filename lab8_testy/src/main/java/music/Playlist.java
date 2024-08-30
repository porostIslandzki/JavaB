package music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Playlist extends ArrayList<Song> {
    //dziedziczy po ArrayList<Song>, co oznacza, że ma
    //wszystkie właściwości i metody ArrayList, w tym isEmpty()

    public Playlist(int initialCapacity) {
        super(initialCapacity);
    }

    public Playlist() {
    }

    public Playlist(Collection<? extends Song> c) {
        super(c);
    }

    public Song atSecond(int totalSeconds) throws IndexOutOfBoundsException {
        int seconds = 0;
        if(totalSeconds < 0){
            throw  new IndexOutOfBoundsException("Czas ujemny");
        }

        for (Song song : this) { // this odnosi się do playlisty (ArrayList<Song>)
            seconds += song.time();
            if (seconds > totalSeconds) {
                return song;
            }
        }
        // Jeśli nie znaleziono utworu w podanym przedziale czasu
        throw new IndexOutOfBoundsException("Czas wykracza poza czas trwania playlisty.");
    }
}
