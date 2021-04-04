package musichub.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() {
        IntLogger sfl = SingletonFileLogger.getInstance();

        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier

        try {
            /*
             * les informations du serveur ( port et adresse IP ou nom d'hote
             * 127.0.0.1 est l'adresse local de la machine
             */
            clientSocket = new Socket("127.0.0.1",5000);

            //flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            //flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread envoyer = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {

                    System.out.println("Bienvenue sur la console client de jMusicHub !");
                    System.out.println("Entrez la commande h si vous avez besoin d'aide sur les commandes disponibles.");

                    while(true){
                        System.out.println("\nQue souhaitez-vous faire ?\n");
                        msg = sc.nextLine();

                        if(msg.equals("q")) {
                            System.out.println("Bye bye !");
                            try {
                                out.close();
                                clientSocket.close();
                            } catch (IOException e) {
                                sfl.write(Levels.ERROR, "Client.Thread.envoyer : erreur à la fermeture de l'application\n" + e.toString());
                            }
                            System.exit(0);
                        }

                        out.println(msg);
                        out.flush();

                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            sfl.write(Levels.ERROR, "Server.Thread.envoi : " + e.toString());
                        }
                    }
                }
            });
            envoyer.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(msg!=null){

                            System.out.println(msg);
                            //System.out.println("Serveur : "+msg);

                            msg = in.readLine();
                        }
                        System.out.println("Serveur déconnecté. L'application va se fermer.");
                        out.close();
                        clientSocket.close();
                        System.exit(0);
                    } catch (IOException e) {
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