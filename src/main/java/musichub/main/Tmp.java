package musichub.main;

//import musichub.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tmp {
    public static void main(String[] args) {



        /*
        // Create two threads:
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    //Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe -c java -cp musichub.util.Server");
                    String[] args = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "musichub.util.server"};
                    Process proc = new ProcessBuilder(args).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec("java -cp musichub.util.Client");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

         */

        //thread1.start();
        //thread2.start();

        /*
        String[] arg1 = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "./src/main/java", "musichub.util.Server"};
        try {
            Process proc = new ProcessBuilder(arg1).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arg2 = new String[] {"cmd", "/c", "start", "cmd.exe", "-c", "java", "-cp", "./src/main/java", "musichub.util.Client"};
        try {
            Process proc = new ProcessBuilder(arg2).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
/*
        ProcessBuilder pb = new ProcessBuilder("echo hello");
        //pb.command("cmd.exe", "/c", "start cmd.exe | echo hello | java -cp ./src/main/java musichub.util.Server");
        try {
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProcessBuilder pb2 = new ProcessBuilder();
        pb2.command("cmd.exe", "/c", "start cmd.exe | java -cp ./src/main/java musichub.util.Client");
        try {
            Thread.sleep(500);
            pb2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

 */

        Scanner scanner = new Scanner(System.in);
        try {
            //where is the repository
            File file = new File("D:\\ESIEA\\3A\\Genie-logiciel-et-Projet-GLPOO\\projet\\Sylvain_Bui rendu 05.04.2021\\sway.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            //waiting for a response

            String response = ""; //

            while(!response.equals("Q")) { //loop for choice
                System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response) { //different function of music
                    case ("P"): clip.start();
                        break;
                    case ("S"): clip.stop();
                        break;
                    case ("R"): clip.setMicrosecondPosition(0);
                        break;
                    case ("Q"): clip.close();
                        break;
                    default: System.out.println("Not a valid response");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Bye!");
    }
}
