package musichub.util;

import musichub.business.UserObject;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class Client {
    UserObject userObject = new UserObject();
    boolean musicPlaying = false;

    public Client() {
        IntLogger sfl = SingletonFileLogger.getInstance();

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
                        if (!musicPlaying) {
                            try {
                                String lastC = userObject.getLastCommand();
                                if (!lastC.equals("C") && !lastC.equals("G") && !lastC.equals("GA") && !lastC.equals("M") && !lastC.equals("MA") && !lastC.equals("PLAY")) {
                                    System.out.println("\nQue souhaitez-vous faire ?\n");
                                }
                                userObject.setCommand(sc.nextLine());

                                if (userObject.getCommand().equals("q")) {
                                    System.out.println("Bye bye !");
                                    try {
                                        out.close();
                                        clientSocket.close();
                                    } catch (IOException e) {
                                        sfl.write(Levels.ERROR, "Client.Thread.envoyer : erreur à la fermeture de l'application\n" + e.toString());
                                    }
                                    System.exit(0);
                                }

                                out.writeObject(userObject);
                                out.flush();

                                Thread.sleep(150);

                            } catch(Exception e){
                                sfl.write(Levels.ERROR, "Server.Thread.envoi : " + e.toString());
                            }
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

                            if(!userObject.getCommand().equals("PLAY") &&userObject.getLastCommand().equals("PLAY") && !userObject.getResponse().equals("Une erreur est survenue à l'ouverture du fichier audio...")) {
                                musicPlaying = true;
                                Clip clip = AudioSystem.getClip();
                                clip.open(userObject.getMusic());

                                String response = "";
                                while(!response.equals("Q")) { //loop for choice
                                    System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
                                    System.out.print("Que souhaitez-vous faire ?");

                                    response = sc.nextLine().toUpperCase();

                                    switch(response) { //different function of music
                                        case ("P"):
                                            clip.start();
                                            break;
                                        case ("S"):
                                            clip.stop();
                                            break;
                                        case ("R"):
                                            clip.setMicrosecondPosition(0);
                                            break;
                                        case ("Q"):
                                            clip.close();
                                            musicPlaying = false;
                                            break;
                                        default: System.out.println("Veuillez entrer une commande valide.");
                                    }
                                }
                            } else {
                                System.out.println(userObject.getResponse());
                            }
                            userObject = (UserObject) in.readObject();
                        }
                        System.out.println("Serveur déconnecté. L'application va se fermer.");
                        out.close();
                        clientSocket.close();
                        System.exit(0);
                    } catch (Exception e) {
                        sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + e.toString());
                        System.out.println("Fermeture de l'application...");
                        System.exit(0);
                    }
                }
            });
            recevoir.start();

        } catch (IOException e) {
            sfl.write(Levels.ERROR, "Server.main() : " + e.toString());
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}