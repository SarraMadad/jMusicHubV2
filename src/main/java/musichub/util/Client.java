package musichub.util;

import musichub.business.Music;
import musichub.business.UserObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    UserObject userObject = new UserObject();
    boolean musicNotPlaying;

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
             * les informations du serveur ( port et adresse IP ou nom d'hote
             * 127.0.0.1 est l'adresse local de la machine
             */
            clientSocket = new Socket("127.0.0.1",5000);

            //flux pour envoyer
            //out = new PrintWriter(clientSocket.getOutputStream());
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
                            //System.out.println(c);
                            userObject.setCommand(input);

                            if(musicNotPlaying) {
                                /*
                                String lastC = userObject.getLastCommand();
                                if (!lastC.equals("C") && !lastC.equals("G") && !lastC.equals("GA") && !lastC.equals("M") && !lastC.equals("MA") && !lastC.equals("PLAY")) {
                                    System.out.println("\nIF\n");
                                }

                                 */

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
                                //userObject.delMusic();

                                musique.playMusic();

                                //userObject = new UserObject();
                                userObject.setResponse("Fin de la lecture.");
                                //out.flush();
                                musicNotPlaying = true;
                            } else {
                                System.out.println(userObject.getResponse());
                            }

                            /* Donnée de debug */
                            System.out.println("commande : " + userObject.getCommand());
                            System.out.println("lastC : " + userObject.getLastCommand());
                            System.out.println("réponse : " + userObject.getResponse());

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