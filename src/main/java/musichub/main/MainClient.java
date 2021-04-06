package musichub.main;

import musichub.business.UserObject;
import musichub.util.IntLogger;
import musichub.util.Levels;
import musichub.util.SingletonFileLogger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainClient extends MainNetwork {

    public MainClient() {
        actualisation();
    }

    public UserObject run(UserObject us) {

        switch(us.getCommand()) {

            /* Affiche les chansons de la bibliothèque. */
            case "B":
                us.setResponse(elements.listeChanson());
                us.setLastCommand("");
                break;

            /* Affiche les livres audio. */
            case "L":
                us.setResponse(elements.listeLivreAudio());
                us.setLastCommand("");
                break;

            /* Affiche les albums. */
            case "A":
                us.setResponse(albums.toString());
                us.setLastCommand("");
                break;

            // C, G et GA ont la même commande de départ
            /* Affiche les chansons d'un album. */
            case "C":
            /* Affiche les chansons triées par genre d'un album. */
            case "G":
            /* Affiche les chansons en ordre aléatoire. */
            case "GA":
                us.setResponse(albums + "\nQuel album souhaitez-vous afficher ?");
                us.setLastCommand(us.getCommand());
                break;

            /* Affiche les playlists. */
            case "P":
                us.setResponse(playlists.toString());
                us.setLastCommand("");
                break;

                // M et MA ont la même commande de départ
            /* Affiche les éléments d'une playlist. */
            case "M":
            /* Affiche les éléments en ordre aléatoire. */
            case "MA":
                us.setResponse(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
                us.setLastCommand(us.getCommand());
                break;

            /* Jouer une musique. */
            case "PLAY":
                us.setResponse(elements.listeChanson() + "\nQuelle musique souhaitez-vous écouter ?\nVeuillez entrer le nom du contenu.");
                us.setLastCommand(us.getCommand());
                break;

            /* Actualise les listes d'éléments de la bibliothèque */
            case "r":
                actualisation();
                us.setResponse("Bibliothèque mise à jour.");
                us.setLastCommand("");
                break;

            /* Menu d'aide aux commandes. */
            case "h":
                us.setResponse(help());
                us.setLastCommand("");
                break;

            default:
                switch (us.getLastCommand()) {
                    /* Affiche les chansons d'un album. */
                    case "C":
                        us.setResponse(albums.displaySongsOfAlbum(us.getCommand()));
                        break;

                    /* Affiche les chansons triées par genre d'un album. */
                    case "G":
                        us.setResponse(albums.displaySongsOfAlbumSorted(us.getCommand()));
                        break;

                    /* Affiche les chansons en ordre aléatoire. */
                    case "GA":
                        us.setResponse(albums.randomDisplaySongsOfAlbum(us.getCommand()));
                        break;

                    /* Affiche les éléments d'une playlist. */
                    case "M":
                        us.setResponse(playlists.displaySongsOfPlaylist(us.getCommand()));
                        break;

                    /* Affiche les éléments en ordre aléatoire. */
                    case "MA":
                        us.setResponse(playlists.randomDisplaySongsOfPlaylist(us.getCommand()));
                        break;

                    /* Jouer une musique. */
                    case "PLAY":
                        us.setMusic(convertMusic(us.getCommand()));
                        us.setResponse("Traitement en cours...");
                        /*
                        File file = new File(".\\files\\library\\" + us.getCommand());
                        try {
                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                            us.setMusic(audioStream);
                        } catch (Exception e) {
                            IntLogger sfl = SingletonFileLogger.getInstance();
                            sfl.write(Levels.ERROR, "MainClient.run().PLAY : erreur à l'ouverture du fichier audio " + e.toString());
                            us.setResponse("Une erreur est survenue à l'ouverture du fichier audio...");
                        }

                         */
                        break;

                    default:
                        us.setResponse("Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.");
                        break;
                }
                break;
        }
        return us;
    }

    private String help() {
        return "L'application jMusicHub accepte les commandes suivantes :"
                + "\n\n					Contenu de la bibliothèque"
                + "\n	B    : affiche la bibliothèque musicale."
                + "\n	L    : affiche les livres audio rangés par auteur."
                + "\n	A    : affiche les albums rangés par date de sortie."
                + "\n	C    : affiche les chansons d'un album."
                + "\n	G    : affiche les chansons rangées par genre d'un album."
                + "\n	GA   : affiche les chansons en ordre aléatoire d'un album."
                + "\n	P    : affiche les playlists."
                + "\n	M    : affiche les éléments d'une playlist."
                + "\n	MA   : affiche les éléments en ordre aléatoire d'une playlist."
                + "\n	PLAY : jouer une musique de la bibliothèque."
                + "\n\n						Utilitaires"
                + "\n	r    : actualise la liste des éléments de la bibliothèque."
                + "\n	q    : quitter l'application.";
    }

    private byte[] convertMusic(String fileName) {
        AudioInputStream audioInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        File file = new File (".\\files\\library\\" + fileName);

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
            //System.out.println("Finally...");
            //tLine.close();
            //System.out.println("Line closed");
            try {
                audioInputStream.close();
                //System.out.println("Stream closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //3rd step
        //System.out.println("Size of the outputStream : "+byteArrayOutputStream.size());

        return byteArrayOutputStream.toByteArray();
    }
}
