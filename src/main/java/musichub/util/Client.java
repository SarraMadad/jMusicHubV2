package musichub.util;

import musichub.business.Music;
import musichub.business.UserObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client socket to exchange with server.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see UserObject
 * @see IntLogger
 */

public class Client {
    /** UserObject that contains the command */
    UserObject userObject = new UserObject();
    /** Local attribute to know when a music is playing */
    boolean musicNotPlaying;

    /**
     * The client is a desktop computer or workstation
     * that is capable of obtaining information and applications from a server.
     */
    public Client() {
        IntLogger sfl = SingletonFileLogger.getInstance();
        musicNotPlaying = true;

        final Socket clientSocket;
        //final BufferedReader in;
        final ObjectInputStream in;
        //final PrintWriter out;
        final ObjectOutputStream out;
        final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

        try {
            /*
             * Connect to the server
             * Port and IP address of server: 9090, localhost 127.0.0.1
             */
            clientSocket = new Socket("127.0.0.1",9090);

            //flux pour envoyer
            //out = new PrintWriter(clientSocket.getOutputStream());
            /* Create input and output to exchange with server. */
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            //flux pour recevoir
            //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            in = new ObjectInputStream(clientSocket.getInputStream());

            Thread envoyer = new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println("Bienvenue sur la console client de jMusicHub !");
                    System.out.println("Entrez la commande h si vous avez besoin d'aide sur les commandes disponibles.");

                    while(true) {
                        try {
                            String input = sc.nextLine();
                            userObject.setCommand(input);

                            if(musicNotPlaying) {

                                if (userObject.getCommand().equals("q")) {
                                    System.out.println("Bye bye !");
                                    try {
                                        userObject.setCommand(null);
                                        out.writeObject(userObject);
                                        out.flush();
                                        out.close();
                                        clientSocket.close();
                                    } catch (IOException e) {
                                        sfl.write(Levels.ERROR, "Client.Thread.envoyer : erreur à la fermeture de l'application\n" + e.toString());
                                    }
                                    System.exit(0);
                                }

                                out.writeObject(userObject);
                                out.flush();
                            }

                            Thread.sleep(300);

                        } catch(Exception e){
                            sfl.write(Levels.ERROR, "Client.Thread.envoi : " + e.toString());
                        }
                    }
                }
            });
            envoyer.start();

            Thread recevoir = new Thread(new Runnable() {
                @Override
                /*
                 * Socket that can communicate between client/server
                 */
                public void run() {
                    try {
                        userObject = (UserObject) in.readObject();
                        while(userObject.getCommand()!=null){

                            if(!userObject.getCommand().equals("PLAY") && userObject.getLastCommand().equals("PLAY")) { // && userObject.getMusic().getData() != null) {
                                musicNotPlaying = false;
                                Music musique;

                                musique = userObject.getMusic();
                                userObject.setLastCommand("");
                                userObject.setCommand("");

                                musique.playMusic();

                                userObject.setResponse("Fin de la lecture.");
                                musicNotPlaying = true;
                            } else {
                                System.out.println(userObject.getResponse());
                            }

                            Thread.sleep(150);

                            userObject = (UserObject) in.readObject();
                        }
                        System.out.println("Serveur déconnecté. L'application va se fermer.");
                        out.close();
                        clientSocket.close();
                        System.exit(0);
                    } catch (Exception e) {
                        sfl.write(Levels.ERROR, "Client.Thread.recevoir : " + e.toString());
                        System.out.println("Fermeture de l'application...");
                        System.exit(0);
                    }
                }
            });
            recevoir.start();

        } catch (IOException e) {
            sfl.write(Levels.ERROR, "Client() : " + e.toString());
        }
    }
}