package musichub.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestChanson {
    @Test
    void testConstructor() {
        Chanson music = new Chanson("Titre", "Artiste", 180, "./files/music.mp3", Genres.POP);

        assertEquals(music.getId(), 0);
    }
}
