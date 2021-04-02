package musichub.util;
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
    static MainServer mainServer = new MainServer();
    static Boolean tmp = false;
    static String tmp2;
    static ServerSocket serveurSocket  ;
    static Socket clientSocket ;
    static BufferedReader in;
    static PrintWriter out;
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] test) {

        try {
            serveurSocket = new ServerSocket(9090);
            clientSocket = serveurSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
            Thread envoi= new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        if(tmp) {
                            msgServer = mainServer.runClient(tmp2);
                            out.println(msgServer);
                            out.flush();
                            tmp = false;
                        } else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            envoi.start();

            Thread recevoir= new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        msgClient = in.readLine();
                        //tant que le client est connecté
                        while(msgClient!=null) {
                            System.out.println("Client : "+msgClient);
                            tmp2 = msgClient;
                            tmp = true;
                            msgClient = in.readLine();
                        }
                        //sortir de la boucle si le client a déconnecté
                        System.out.println("Client déconnecté");
                        //fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();

            Thread main= new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mainServer.MAINTMP();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            main.start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void envoiClient(String msg) {

    }
}