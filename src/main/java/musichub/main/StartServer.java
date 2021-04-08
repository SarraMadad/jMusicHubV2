package musichub.main;

import musichub.util.Client;
import musichub.util.Server;

/**
 * StartClient class to launch Server.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Server
 */

public class StartServer {
    /**
     * default constructor
     * start a server console
     */
    public StartServer() {
        new Server();
    }

    /**
     * main entry for the server to program
     * @param args command arguments
     */
    public static void main(String[] args) {
        new StartServer();
    }
}
