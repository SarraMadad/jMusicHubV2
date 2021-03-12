package musichub.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlbum {
    @Test
    void AlbumConstructor() {
        Collection<Chanson> list = new ArrayList<>();
        Album album = new Album(5, "Test", 10, list, "Machin", "01-01-1999");

        assertEquals(album.getId(), 5);

        Chanson chanson = new Chanson(7, "Musique", 10, "M. Machin", "ici.mp3", Genre.CLASSIQUE, "01-01-1999");
        album.addChanson(chanson);
    }
}
