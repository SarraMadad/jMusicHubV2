package musichub.util;

import musichub.business.UserObject;
import musichub.main.MainClient;
import musichub.main.MainServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * www.projetSNIR_LOUATY.com
 */
public class Server {

    String msgClient;
    String msgServer;
    MainClient mainClient = new MainClient();
    Boolean commandeClient = false;
    ServerSocket serveurSocket  ;
    Socket clientSocket ;
    //BufferedReader in;
    ObjectInputStream in;
    //PrintWriter out;
    ObjectOutputStream out;

    UserObject userObject = new UserObject();

    public Server() {
        IntLogger sfl = SingletonFileLogger.getInstance();

        try {
            Thread main= new Thread(new Runnable() {
                @Override
                public void run() {
                    new MainServer();
                }
            });
            main.start();

            serveurSocket = new ServerSocket(5000);
            clientSocket = serveurSocket.accept();
            sfl.write(Levels.INFO, "Client connecté");

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Thread envoi= new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        try {
                            if (commandeClient) {
                                userObject = mainClient.run(userObject);
                                out.writeObject(userObject);
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
                            userObject = (UserObject) in.readObject();
                            //tant que le client est connecté
                            while (userObject.getCommand() != null) {
                                //System.out.println("Client : " + msgClient);
                                commandeClient = true;
                                Thread.sleep(150);
                                userObject = (UserObject) in.readObject();
                            }

                            //sortir de la boucle si le client est déconnecté
                            //System.out.println("Client déconnecté");

                            out.close();
                            clientSocket.close();
                            sfl.write(Levels.INFO, "Client déconnecté");

                            clientSocket = serveurSocket.accept();
                            sfl.write(Levels.INFO, "Client connecté");
                            out = new ObjectOutputStream(clientSocket.getOutputStream());
                            in = new ObjectInputStream(clientSocket.getInputStream());

                            //fermer le flux et la session socket
                            //out.close();
                            //clientSocket.close();
                            //serveurSocket.close();
                            //System.exit(0);
                        } catch (EOFException e) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException interruptedException) {
                                sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + interruptedException.toString());
                            }
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

    public static void main(String[] test) {
        new Server();
    }
}