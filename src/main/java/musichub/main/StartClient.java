package musichub.main;

import musichub.util.Client;
import musichub.util.IntLogger;
import musichub.util.Levels;

/**
 * StartClient class to launch client.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Client
 */

public class StartClient {
    public StartClient() {
        new Client();
    }

    public static void main(String[] args) {
        new StartClient();
    }
}
