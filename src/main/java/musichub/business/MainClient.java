package musichub.business;

/**
 * MainClient contains the main methods for Client program.
 * <p>
 * It contains the UserObject and Music are used
 * to display songs.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see UserObject
 * @see Music
 */


public class MainClient extends MainNetwork {

    /**
     * default constructor of Mainclient
     * Read from XML file
     * @see MainNetwork#actualisation()
     */
    public MainClient() {
        actualisation();
    }

    /**
     * client command to execute
     * @param us UserObject Command
     * @return Server respond
     */
    public UserObject run(UserObject us) {

        switch(us.getCommand()) {

            /* Displays the songs in the library. */
            case "B":
                us.setResponse(elements.listeChanson() + "\nQue souhaitez-vous faire ?\n");
                us.setLastCommand("");
                break;

            /* Displays audio books. */
            case "L":
                us.setResponse(elements.listeLivreAudio() + "\nQue souhaitez-vous faire ?\n");
                us.setLastCommand("");
                break;

            /* Displays albums. */
            case "A":
                us.setResponse(albums.toString() + "\nQue souhaitez-vous faire ?\n");
                us.setLastCommand("");
                break;

            /* Displays the songs from album. */
            case "C":
            /* Displays the songs selected by type from album. */
            case "G":
            /* Displays the songs in random order. */
            case "GA":
                us.setResponse(albums + "\nQuel album souhaitez-vous afficher ?");
                us.setLastCommand(us.getCommand());
                break;

            /* Displays playlists. */
            case "P":
                us.setResponse(playlists.toString() + "\nQue souhaitez-vous faire ?\n");
                us.setLastCommand("");
                break;

             //M and Ma have the same command
            /* Displays the items in a playlist. */
            case "M":
            /* Displays the elements in random order. */
            case "MA":
                us.setResponse(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
                us.setLastCommand(us.getCommand());
                break;

            /* Play music */
            case "PLAY":
                us.setResponse(elements.listeChanson() + "\nQuelle musique souhaitez-vous écouter ?\nVeuillez entrer le nom du contenu.");
                us.setLastCommand(us.getCommand());
                break;

            /* Refreshes the lists of items in library */
            case "r":
                actualisation();
                us.setResponse("Bibliothèque mise à jour." + "\nQue souhaitez-vous faire ?\n");
                us.setLastCommand("");
                break;

            /* Command help menu. */
            case "h":
                us.setResponse(help());
                us.setLastCommand("");
                break;

            default:
                switch (us.getLastCommand()) {
                    /* Displays the songs of an album.*/
                    case "C":
                        us.setResponse(albums.displaySongsOfAlbum(us.getCommand()) + "\nQue souhaitez-vous faire ?\n");
                        break;

                    /* Displays the songs selected by type from album. */
                    case "G":
                        us.setResponse(albums.displaySongsOfAlbumSorted(us.getCommand()) + "\nQue souhaitez-vous faire ?\n");
                        break;

                    /* Displays the songs in random order. */
                    case "GA":
                        us.setResponse(albums.randomDisplaySongsOfAlbum(us.getCommand()) + "\nQue souhaitez-vous faire ?\n");
                        break;

                    /* Displays the items in a playlist. */
                    case "M":
                        us.setResponse(playlists.displaySongsOfPlaylist(us.getCommand()) + "\nQue souhaitez-vous faire ?\n");
                        break;

                    /* Displays items in random order */
                    case "MA":
                        us.setResponse(playlists.randomDisplaySongsOfPlaylist(us.getCommand()) + "\nQue souhaitez-vous faire ?\n");
                        break;

                    /* Play musique. */
                    case "PLAY":
                        Music musique = new Music();
                        musique.setData(musique.convertMusic(us.getCommand()));

                        if(musique.getData() == null) {
                            us.setLastCommand("");
                            us.setResponse("Impossible de lire cette musique.\nQue souhaitez-vous faire ?\n");
                        } else {
                            us.setMusic(musique);
                        }

                        break;

                    default:
                        us.setResponse("Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.");
                        break;
                }
                break;
        }
        return us;
    }

    /**
     * Method to return the help menu to the client
     * @return command menu
     */
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
                + "\n	q    : quitter l'application."
                + "\n\nQue souhaitez-vous faire ?\n";
    }
}
