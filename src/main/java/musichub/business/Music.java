package musichub.business;

import musichub.util.IntLogger;
import musichub.util.Levels;
import musichub.util.SingletonFileLogger;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Object to store the music data to send
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see UserObject
 *
 */


public class Music implements Serializable {
    /**
     * Music stored as a byte array
     */
    private byte[] data;

    /**
     * default constructor Music
     */
    public Music() {
    }

    /**
     * get the music as a byte array
     * @return byte array
     */
    public byte[] getData() {
        return data;
    }

    /**
     * set the music as a byte array
     * @param data byte array
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * convert the music into a byte array
     * @param fileName directory of our folder
     * @return return the byte
     */
    public byte[] convertMusic(String fileName) {
        AudioInputStream audioInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        byte[] musicData;
        if(!fileName.contains(".wav")) {
            fileName += ".wav";
        }
        File file = new File (".\\files\\library\\" + fileName);


        try {
            audioInputStream=AudioSystem.getAudioInputStream(file);
            AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
            AudioSystem.write(audioInputStream, targetType, byteArrayOutputStream);
        } catch (Exception e) {
            IntLogger sfl = SingletonFileLogger.getInstance();
            sfl.write(Levels.ERROR, "UserObject.convertMusic() : " + e.toString());
        } finally {
            try {
                audioInputStream.close();
                musicData = byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                IntLogger sfl = SingletonFileLogger.getInstance();
                sfl.write(Levels.ERROR, "UserObject.convertMusic() : " + e.toString());
                musicData = null;
            }
        }

        return musicData;
    }

    /**
     * function that play the music
     * different function like play, pause, reset
     */
    public void playMusic() {

        Scanner sc = new Scanner(System.in);
        ByteArrayInputStream bis = new ByteArrayInputStream(data);

        AudioFormat audioFormat = new AudioFormat(44100, 16, 2, true, false);
        AudioInputStream audioInputStream =new AudioInputStream(bis, audioFormat, data.length);

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            String response = "";
            while (!response.equals("QUIT")) { //loop for choice
                System.out.println("P = play, S = Stop, R = Reset, QUIT = Quit");
                System.out.println("Que souhaitez-vous faire avec votre musique ?");

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
                    case ("QUIT"):
                        clip.close();
                        break;
                    default:
                        System.out.println("Veuillez entrer une commande valide.");
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
            IntLogger sfl = SingletonFileLogger.getInstance();
            sfl.write(Levels.ERROR, "UserObject.playMusic() : Erreur à la lecture du fichier " + e.toString());
            System.out.println("Une erreur est survenue...");
        }

        System.out.println("Arrêt de la lecture de la musique." + "\nQue souhaitez-vous faire ?\n");
    }
}
