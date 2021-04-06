package musichub.main;

//import musichub.util.*;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

        /*
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

         */

        SourceDataLine sLine = null;
        AudioFormat audioFormat;
        AudioInputStream audioInputStream = null;
        AudioInputStream audioInputStream2;
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        File file = new File (".\\files\\library\\bruh.wav");
                /*
                 * The aim of this code is to test the possibility or creating a mixing console in JAVA like the physical mixing consoles
                 * First step : Create an inputstream from a file, a microphone or anything else
                 * This is like plugging a source to a line-in of a stripe of a mixing console
                 * Second : Reading this input stream and create a byte array
                 * This byte array will be sent to the "master output" of the mixing console
                 * Question to answer later : how to create a byte array that will act like a buffer for the next steps...
                 * Third : Using this byte array to create an audioInputStream
                 * This step is like having the "master volume" getting what is sent by the stripe.
                 * Fourth : reading this audioInputStream
                 * The sound should flow out to the speaker
                 */

                //1st step
                try {
                    audioInputStream=AudioSystem.getAudioInputStream(file);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;

                //2nd step
                try {
                    AudioSystem.write(audioInputStream, targetType, byteArrayOutputStream);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Finally...");
                    //tLine.close();
                    System.out.println("Line closed");
                    try {
                        audioInputStream.close();
                        System.out.println("Stream closed.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                /////////////////////////////////////////////////////////////

                //3rd step
                System.out.println("Size of the outputStream : "+byteArrayOutputStream.size());
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                System.out.println("Size of byte array : "+byteArray.length);
                ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);

                audioFormat = new AudioFormat(44100, 16, 2, true, false);
                audioInputStream2=new AudioInputStream(bis, audioFormat, byteArray.length);

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
                try  {
                    //System.out.println(info);
                    sLine=(SourceDataLine) AudioSystem.getLine(info);
                    System.out.println(sLine.getLineInfo());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    sLine.open(audioFormat);
                } catch (Exception e){
                    e.printStackTrace();
                }
                sLine.start();
                System.out.println("Line Started");


                //4th step
                try {
                    byte[] bytes =  new byte[1024];
                    int bytesRead=0;
                    int loop=0;
                    while ((bytesRead=audioInputStream2.read(bytes, 0, bytes.length))!= -1) {
                        //getVolumeLevel(bytes);
                        try {
                            sLine.write(bytes, 0, bytesRead);
                            System.out.println(loop);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        loop+=1;
                    }
                    System.out.println("No bytes anymore !");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Line stopped");
    }
}
