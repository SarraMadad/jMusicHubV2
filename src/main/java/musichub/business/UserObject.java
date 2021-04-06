package musichub.business;

import musichub.util.IntLogger;
import musichub.util.Levels;
import musichub.util.SingletonFileLogger;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Scanner;

public class UserObject implements Serializable {
    private String command;
    private String lastCommand;
    private String response;
    private byte[] music;

    public UserObject() {
        command = "";
        lastCommand = "";
        response = "";
        music = null;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public byte[] getMusic() {
        return music;
    }

    public void setMusic(byte[] music) {
        this.music = music;
    }

    public void playMusic(byte[] byteArrayMusic) {
        Scanner sc = new Scanner(System.in);
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArrayMusic);

        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, false);
        AudioInputStream audioInputStream =new AudioInputStream(bis, audioFormat, byteArrayMusic.length);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            String response = "";
            while (!response.equals("Q")) { //loop for choice
                System.out.println("P = play, S = Stop, R = Reset, Q = Quit");
                System.out.println("Que souhaitez-vous faire ?");

                response = sc.nextLine().toUpperCase();

                switch (response) { //different function of music
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
                        break;
                    default:
                        System.out.println("Veuillez entrer une commande valide.");
                }
            }
        } catch (Exception e) {
            IntLogger sfl = SingletonFileLogger.getInstance();
            sfl.write(Levels.ERROR, "UserObject.playMusic() : Erreur à la lecture du fichier " + e.toString());
            System.out.println("Une erreur est survenue...");
        }

        System.out.println("Arrêt de la lecture de la musique.");
        System.out.println("\nQue souhaitez-vous faire ?\n");

        /*
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

         */
    }
}
