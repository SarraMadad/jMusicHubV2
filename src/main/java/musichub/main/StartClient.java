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
    /**
     * default constructor
     * start a client console
     */
    public StartClient() {
        new Client();
    }

    /**
     * main entry for the client to program
     * @param args command arguments
     */
    public static void main(String[] args) {
        new StartClient();
    }
}
