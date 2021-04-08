package musichub.util;

import musichub.business.UserObject;
import musichub.business.MainClient;
import musichub.business.MainServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Server socket to exchange with Client.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see IntLogger
 */

public class Server {

    /** Launch MainClient program */
    MainClient mainClient = new MainClient();
    /** Boolean to know when a client's command is running */
    Boolean commandeClient = false;
    /** Server's socket */
    ServerSocket serveurSocket  ;
    /** Client's socket */
    Socket clientSocket ;
    /** Input stream */
    ObjectInputStream in;
    /** Output stream */
    ObjectOutputStream out;
    /** User command */
    UserObject userObject = new UserObject();

    /**
     * Constructor of the server
     */
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
            //create the socket server object
            serveurSocket = new ServerSocket(5000);
            //creating socket and waiting for client connection
            clientSocket = serveurSocket.accept();

           sfl.write(Levels.INFO, "Client connecté");

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            /* read data from client and send data to client.*/

            Thread envoi= new Thread(new Runnable() {
                @Override
                /*
                 *
                 */
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
                            //while the client is connected
                            while (userObject.getCommand() != null) {
                                commandeClient = true;
                                Thread.sleep(150);
                                userObject = (UserObject) in.readObject();
                            }

                            out.close();
                            clientSocket.close();
                            sfl.write(Levels.INFO, "Client déconnecté");

                            clientSocket = serveurSocket.accept();
                            sfl.write(Levels.INFO, "Client connecté");
                            out = new ObjectOutputStream(clientSocket.getOutputStream());
                            in = new ObjectInputStream(clientSocket.getInputStream());

                        } catch (EOFException e) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException interruptedException) {
                                sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + interruptedException.toString());
                            }
                        } catch (Exception e) {
                            sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + e.toString());
                            try {
                                Thread.sleep(150);
                            } catch (InterruptedException interruptedException) {
                                sfl.write(Levels.ERROR, "Server.Thread.recevoir : " + interruptedException.toString());
                            }
                        }
                    }
                }
            });
            recevoir.start();

        }catch (IOException e) {
            sfl.write(Levels.ERROR, "Server() : " + e.toString());
        }
    }
}