package musichub.util;

import musichub.main.MainClient;
import musichub.main.MainServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * www.projetSNIR_LOUATY.com
 */
public class Server {

    static String msgClient;
    static String msgServer;
    static MainClient mainClient = new MainClient();
    static Boolean commandeClient = false;
    static ServerSocket serveurSocket  ;
    static Socket clientSocket ;
    static BufferedReader in;
    static PrintWriter out;
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] test) {
        IntLogger sfl = SingletonFileLogger.getInstance();

        try {
            Thread main= new Thread(new Runnable() {
                @Override
                public void run() {
                    MainServer mainServer = new MainServer();
                }
            });
            main.start();

            serveurSocket = new ServerSocket(5000);
            clientSocket = serveurSocket.accept();
            sfl.write(Levels.INFO, "Client connecté");

            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));

            Thread envoi= new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if (commandeClient) {
                                msgServer = mainClient.run(msgClient);
                                out.println(msgServer);
                                out.flush();
                                commandeClient = false;
                            } else {
                                Thread.sleep(100);
                            }
                        } catch (Exception e) {
                            sfl.write(Levels.ERROR, "Server.Thread.envoi : " + e.toString());
                        }
                    }
                }
            });
            envoi.start();

            Thread recevoir= new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            msgClient = in.readLine();
                            //tant que le client est connecté
                            while (msgClient != null) {
                                //System.out.println("Client : " + msgClient);
                                commandeClient = true;
                                Thread.sleep(150);
                                msgClient = in.readLine();
                            }

                            //sortir de la boucle si le client a déconnecté
                            //System.out.println("Client déconnecté");

                            out.close();
                            clientSocket.close();
                            sfl.write(Levels.INFO, "Client déconnecté");

                            clientSocket = serveurSocket.accept();
                            sfl.write(Levels.INFO, "Client connecté");
                            out = new PrintWriter(clientSocket.getOutputStream());
                            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));

                            //fermer le flux et la session socket
                            //out.close();
                            //clientSocket.close();
                            //serveurSocket.close();
                            //System.exit(0);
                        } catch (Exception e) {
                            sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + e.toString());
                        }
                    }
                }
            });
            recevoir.start();

        }catch (IOException e) {
            sfl.write(Levels.ERROR, "Server.main() : " + e.toString());
        }
    }
}