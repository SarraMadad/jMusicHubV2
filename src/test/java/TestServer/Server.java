package TestServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    public static int PORT = 9090;
    public static void main (String[] args) throws IOException {
        ServerSocket listener = new ServerSocket (PORT);
        System.out.println("[SERVER] Waiting for client connect...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client");

        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        String date = new Date().toString();
        System.out.println("[SERVER] Sent Date " + date);
        out.println(date);

        System.out.println("[SERVER] Sent Date. Closing...");
        client.close();
        listener.close();
    }
}
