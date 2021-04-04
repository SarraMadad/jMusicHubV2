package musichub.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    @Test
    void testConnexionServeur() {
        try {
            Thread server= new Thread(new Runnable() {
                @Override
                public void run() {
                    Server s = new Server();
                }
            });
            System.out.println("Message de bienvenue du serveur. A ignorer.\n\n");
            // Démarrage en parallèle du serveur
            server.start();


            Socket clientSocket = new Socket("127.0.0.1", 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            String commande = "test";
            out.println(commande);
            out.flush();

            // Temps d'attente pour être sûr de recevoir une réponse
            Thread.sleep(500);

            String reponse = in.readLine();

            System.out.println("\n\nMessage attendu : Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.");
            System.out.println("Message reçu : " + reponse);
            assertEquals("Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.", reponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
