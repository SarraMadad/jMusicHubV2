package musichub.util;

import musichub.business.UserObject;
import org.junit.jupiter.api.Test;

import java.io.*;
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
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            UserObject us = new UserObject();
            us.setCommand("test");

            out.writeObject(us);
            out.flush();

            // Temps d'attente pour être sûr de recevoir une réponse
            Thread.sleep(500);

            us = (UserObject) in.readObject();

            System.out.println("\n\nMessage attendu : Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.");
            System.out.println("Message reçu : " + us.getResponse());
            assertEquals("Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.", us.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
