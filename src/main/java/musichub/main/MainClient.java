package musichub.main;

public class MainClient extends MainNetwork {

    public MainClient() {
        actualisation();
    }

    public String run(String commande) {

        /* TODO("Finir les commandes") */

        String message = "rien";

        switch(commande) {

            /* Affiche les chansons de la bibliothèque. */
            case "B":
                //System.out.println(elements.listeChanson());
                message = elements.listeChanson().toString();
                break;

            /* Affiche les livres audio. */
            case "L":
                message = elements.listeLivreAudio().toString();
                break;

            /* Affiche les albums. */
            case "A":
                message = albums.toString();
                break;

            /* Affiche les chansons d'un album. */
            case "C":
                /*
                System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
                userInput = userInputObj.nextLine();

                albums.displaySongsOfAlbum(userInput);

                 */
                break;

            /* Affiche les chansons triées par genre d'un album. */
            case "G":
                /*
                System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
                userInput = userInputObj.nextLine();

                albums.displaySongsOfAlbumSorted(userInput);

                 */
                break;

            /* Affiche les chansons en ordre aléatoire. */
            case "GA":
                /*
                System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
                userInput = userInputObj.nextLine();

                albums.randomDisplaySongsOfAlbum(userInput);

                 */
                break;

            /* Affiche les playlists. */
            case "P":
                message = playlists.toString();
                break;

            /* Affiche les éléments d'une playlist. */
            case "M":
                /*
                System.out.println(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
                userInput = userInputObj.nextLine();

                playlists.displaySongsOfPlaylist(userInput);

                 */
                break;

            /* Affiche les éléments en ordre aléatoire. */
            case "MA":
                /*
                System.out.println(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
                userInput = userInputObj.nextLine();

                playlists.randomDisplaySongsOfPlaylist(userInput);

                 */
                break;

            /* Jouer une musique. */
            case "PLAY":
                //TODO
                break;

            /* Actualise les listes d'éléments de la bibliothèque */
            case "r":
                actualisation();
                message = "Bibliothèque mise à jour.";
                break;

            /* Menu d'aide aux commandes. */
            case "h":
                message = help();
                break;

            default:
                message = "Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.";
                break;
        }
        return message;
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
                + "\n   PLAY : jouer une musique de la bibliothèque."
                + "\n\n						Utilitaires"
                + "\n	r    : actualise la liste des éléments de la bibliothèque."
                + "\n	q    : quitter l'application.";
    }
}
