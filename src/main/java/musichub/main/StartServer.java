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
    public StartServer() {
        new Server();
    }

    public static void main(String[] args) {
        new StartServer();
    }
}
