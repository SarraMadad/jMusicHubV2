package musichub.main;

import java.util.Scanner;
import musichub.business.*;
import musichub.util.*;

import java.util.*;
import java.io.*;
import java.time.*;

/**
 * jMusicHub contains the main method for the program.
 * <p>
 * It contains the ElementList, AlbumList and PlaylistList objects and is used
 * to serialise and deserialize those objects into XML files.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see ElementList
 * @see AlbumList
 * @see PlaylistList
 */
public class jMusicHub implements interfaceJmusicHub {
	/** Retrieve user input. */
	Scanner userInputObj = new Scanner(System.in);
	/** Save user input. */
	String userInput = null;
	/** List of all the elements. */
	ElementList elements = null;
	/** List of all the albums. */
	AlbumList albums = null;
	/** List of all the playlists. */
	PlaylistList playlists = null;

	/**
	 * Default constructor. Initializes the lists and contains the main while.
	 */
	public jMusicHub() {

		/* Lecture des fichiers XML. */
		elements = new DeserializeFromXML("elements").decodeElements();
		albums = new DeserializeFromXML("albums").decodeAlbums();
		playlists = new DeserializeFromXML("playlists").decodePlaylists();

		Boolean run = true;

		System.out.println("Bienvenue client dans jMusicHub !");
		System.out.println("Entrez la commande h si vous avez besoin d'aide sur les commandes disponibles.");

		while(run) {
			System.out.println("\nQue souhaitez-vous faire ?\n");
			userInput = userInputObj.nextLine();
			switch(userInput) {

				/* Affiche les chansons de la bibliothèque. */
				case "B":
					System.out.println(elements.listeChanson());
					break;

				/* Affiche les livres audio. */
				case "L":
					System.out.println(elements.listeLivreAudio());
					break;

				/* Affiche les albums. */
				case "A":
					System.out.println(albums);
					break;

				/* Affiche les chansons d'un album. */
				case "C":
			        System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
			        userInput = userInputObj.nextLine();

			        albums.displaySongsOfAlbum(userInput);
					break;

				/* Affiche les chansons triées par genre d'un album. */
				case "G":
					System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
			        userInput = userInputObj.nextLine();

					albums.displaySongsOfAlbumSorted(userInput);
					break;

				/* Affiche les chansons en ordre aléatoire. */
				case "GA":
					System.out.println(albums + "\nQuel album souhaitez-vous afficher ?");
			        userInput = userInputObj.nextLine();

					albums.randomDisplaySongsOfAlbum(userInput);
					break;

				/* Affiche les playlists. */
				case "P":
					System.out.println(playlists);
					break;

				/* Affiche les éléments d'une playlist. */
				case "M":
					System.out.println(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
			        userInput = userInputObj.nextLine();

					playlists.displaySongsOfPlaylist(userInput);
					break;

				/* Affiche les éléments en ordre aléatoire. */
				case "MA":
					System.out.println(playlists + "\nQuelle playlist souhaitez-vous afficher ?");
			        userInput = userInputObj.nextLine();

					playlists.randomDisplaySongsOfPlaylist(userInput);
					break;

				/* Nouvelle chanson. */
				case "b":
					addMusic(elements);
					break;

				/* Nouveau livre audio. */
				case "l":
					addLivre(elements);
					break;

				/* Nouvel album. */
				case "a":
					addAlbum(albums);
					break;

				/* Rajout d'une chanson existante à un album. */
				case "c":
                    System.out.println(albums + "\nQuel album souhaitez-vous modifier ?");
                    String albumName = userInputObj.nextLine();
                    System.out.println(elements.listeChanson() + "\nQuelle musique souhaitez-vous ajouter ?\nEntrez le titre de la musique suivi de son artiste : Titre - Artiste");
                    String musicName = userInputObj.nextLine();
                    albums.addMusic(albumName, musicName, elements);
                    break;

                  /* Nouvelle playlist. */
				case "p":
					addPlaylist(playlists);
					break;

				/* Rajout d'un élément existant à une playlist. */
				case "m":
			        System.out.println(playlists + "\nQuelle playlist souhaitez-vous modifier ?");
                    String playlistName = userInputObj.nextLine();
                    System.out.println(elements.listeChanson() + "\n" + elements.listeLivreAudio());
                    System.out.println("Quel élément souhaitez-vous ajouter ?\nEntrez le titre de l'élément suivi de son artiste/auteur : Titre - Artiste");
                    String elementName = userInputObj.nextLine();
                    playlists.addElement(playlistName, elementName, elements);
					break;

				/* Supprimer une chanson. */
				case "-b":
					delMusic(elements, albums, playlists);
					break;

				/* Supprimer un livre audio. */
				case "-l":
					delLivre(elements, playlists);
					break;

				/* Supprimer un album. */
				case "-a":
					delAlbum(albums);
					break;

				/* Supprimer une chanson d'un album. */
				case "-c":
			        System.out.println(albums + "\nQuel album souhaitez-vous modifier ?");
                    albumName = userInputObj.nextLine();
                    albums.displaySongsOfAlbum(albumName);
                    System.out.println("Quelle musique souhaitez-vous supprimer ?");
                    System.out.println("Entrez le titre de la musique suivi de son artiste : Titre - Artiste");
                    musicName = userInputObj.nextLine();
                    albums.delSongOfAlbum(albumName, musicName, elements);
                    break;

				/* Supprimer une playlist. */
				case "-p":
					delPlaylist(playlists);
					break;

				/* Supprimer un élément d'une playlist. */
				case "-m":
			        System.out.println(playlists + "\nQuelle playlist souhaitez-vous modifier ?");
                    playlistName = userInputObj.nextLine();
                    playlists.displaySongsOfPlaylist(playlistName);
                    System.out.println("Quel élément souhaitez-vous supprimer ?\nEntrez le titre de l'élément suivi de son artiste/auteur : Titre - Artiste");
                    elementName = userInputObj.nextLine();
                    playlists.delSongOfPlaylist(playlistName,elementName,elements);
                    break;

				/* Ecriture dans les fichiers XML. */
				case "s":
					System.out.println("Sauvegarde de la bibliothèque musicale...");
					new SerializeToXML("elements", elements);
					new SerializeToXML("albums", albums);
					new SerializeToXML("playlists", playlists);
					System.out.println("Sauvegarde terminée.");
					break;

				/* Menu d'aide aux commandes. */
				case "h":
					help();
					break;

				/* Quitter. */
				case "q":
					run = quit();
					break;

				default:
					System.out.println("Commande introuvable. Utilisez la commande h pour afficher les commandes disponibles.");
					break;
			}
		}
	}

	/**
	 * Main entry for the program.
	 * 
	 * @param args user arguments. Not used.
	 */
	public static void main(String[] args) {
		new jMusicHub();
	}

	/**
	 * Returns the title entered by the user.
	 * 
	 * @return the title as a String
	 */
	public String newTitre() {
		System.out.print("\nTitre : ");
		String titre = userInputObj.nextLine();
		return titre;
	}

	/**
	 * Returns the name entered by the user.
	 * 
	 * @return the name as a String
	 */
	public String newNom() {
		System.out.print("\nNom : ");
		String nom = userInputObj.nextLine();
		return nom;
	}

	/**
	 * Returns the artist entered by the user.
	 * 
	 * @return the artist as a String
	 */
	public String newArtiste(){
		System.out.print("\nArtiste : ");
		String artiste = userInputObj.nextLine();
		return artiste;
	}
	
	/**
	 * Returns the writer entered by the user.
	 * 
	 * @return the writer as a String
	 */
	public String newAuteur(){
		System.out.print("\nAuteur : ");
		String auteur = userInputObj.nextLine();
		return auteur;
	}

	/**
	 * Returns the length entered by the user. Checks if the value is a number.
	 * 
	 * @return the length as an int
	 */
	public int newDuree(){
		String duree = null;
		while(true) {
			System.out.print("\nDurée (en secondes) : ");
		 	duree = userInputObj.nextLine();
		 	try {
			 	return Integer.parseInt(duree);
		 	} catch (NumberFormatException nfe) {
		 		IntLogger sfl = SingletonFileLogger.getInstance();
		 		sfl.write(Levels.ERROR, "jMusicHub.newDuree() : user input is not a valid number.");
		 		System.out.println("Veuillez entrer un nombre.");
		 	}
		}
	}

	/**
	 * Returns the location entered by the user.
	 * 
	 * @return the location as a String
	 */
	public String newContenu(){
		System.out.print("\nContenu (nom du fichier) : ");
		String contenu = userInputObj.nextLine();
		return contenu;
	}

	/**
	 * Returns the category entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the category as a String
	 * @throws WrongEnumValue if an input exception occurred
	 * @see musichub.business.WrongEnumValue
	 */
	public String newCategorie() throws WrongEnumValue {
		System.out.println("\nCatégories disponibles sont : JEUNESSE ; ROMAN ; THEATRE ; DISCOURS ; DOCUMENTAIRE");
		System.out.print("Catégorie : ");
		String categorie = userInputObj.nextLine().toUpperCase();
		if(!categorie.equals("JEUNESSE") && 
			!categorie.equals("ROMAN") && 
			!categorie.equals("THEATRE") && 
			!categorie.equals("DISCOURS") && 
			!categorie.equals("DOCUMENTAIRE") && 
			!categorie.equals("QUIT")) {
			throw new WrongEnumValue("Catégorie erronée. Veuillez entrer une valeur correcte.");
		}
		return categorie;
	}

	/**
	 * Returns the date entered by the user.
	 * 
	 * @return the date as a String
	 */
	public String newDate(){
		while(true) {
			System.out.println("\nEntrez la date de sortie de l'album en format numérique.");
			System.out.print("Année : ");
			String annee = userInputObj.nextLine();
			System.out.println();
			System.out.print("Mois : ");
			String mois = userInputObj.nextLine();
			System.out.println();
			System.out.print("Jour : ");
			String jour = userInputObj.nextLine();
			System.out.println();

			try {
				LocalDate date = LocalDate.of(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour));
				return String.valueOf(date);
			} catch(NumberFormatException nfs) {
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.ERROR, "jMusicHub.newDate() : user input is not a valid number.");
				System.out.println("Format incorrect, veuillez entrer une valeur correcte.");
			}
		}
	}

	/**
	 * Returns the language entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the language as a String
	 * @throws WrongEnumValue if an input exception occurred
	 * @see musichub.business.WrongEnumValue
	 */
	public String newLangue() throws WrongEnumValue {
		System.out.println("\nLes langues disponibles sont : FRANCAIS ; ESPAGNOL ; ITALIEN ; ALLEMAND ; ANGLAIS ");
		System.out.print("Langue : ");
		String langue = userInputObj.nextLine().toUpperCase();

		if(!langue.equals("FRANCAIS") && 
		   !langue.equals("ESPAGNOL") && 
		   !langue.equals("ITALIEN") && 
		   !langue.equals("ALLEMAND") && 
		   !langue.equals("ANGLAIS") && 
		   !langue.equals("QUIT")) {
			throw new WrongEnumValue("Langue erronée. Veuillez entrer une valeur correcte.");
		}
		return langue;
	}

	/**
	 * Returns the type entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the type as a String
	 * @throws WrongEnumValue if an input exception occurred
	 * @see musichub.business.WrongEnumValue
	 */
	public String newGenre() throws WrongEnumValue {
		System.out.println("\nLes genres disponibles sont : JAZZ ; CLASSIQUE ; HIP_HOP ; ROCK ; POP ; RAP");
		System.out.print("Genre : ");
		String genre = userInputObj.nextLine().toUpperCase();

		if(!genre.equals("JAZZ") &&
		   !genre.equals("CLASSIQUE") &&
		   !genre.equals("HIP_HOP") &&
		   !genre.equals("ROCK") &&
		   !genre.equals("POP") && 
		   !genre.equals("RAP") &&
		   !genre.equals("QUIT")) {
			throw new WrongEnumValue("Genre erroné. Veuillez entrer une valeur correcte.");
		}
		return genre;
	}

	/**
	 * Checks the value entered by the user to abort the creation of the
	 * new element.
	 * 
	 * @param valeur user value to check
	 * @return 0 (continue) or 1 (abort)
	 */
	public boolean ifWantToQuit(String valeur){
		if(valeur.equals("QUIT")){
			System.out.println("Abandon de l'opération.");
			return true;
		}
			return false;
	}

	/**
	 * Adds a song to the ElementList and displays it.
	 * 
	 * @param elements the ElementList to modify
	 */
	public void addMusic(ElementList elements){
		System.out.println("~~~Nouvelle chanson~~~");
		System.out.println("Entrez la commande QUIT à n'importe quel moment pour annuler l'ajout de la nouvelle chanson.");

		String titre = newTitre();
		if (ifWantToQuit(titre)){
			return;
		}

		String artiste = newArtiste();
		if (ifWantToQuit(artiste)){
			return;
		}

		int duree = newDuree();
		if (ifWantToQuit(String.valueOf(duree))){
			return;
		}

		String contenu = newContenu();
		if (ifWantToQuit(contenu)){
			return;
		}

		String genre = null;
		Boolean wrongValue = true;
		while(wrongValue) {
			try {
				genre = newGenre();
				wrongValue = false;
				if (ifWantToQuit(genre)){
					return;
				}
			} catch(WrongEnumValue ex) {
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.ERROR, "jMusicHub.addMusic() : user input is not a valid Genre");
				System.err.println(ex.getMessage());
			}
		}

		elements.add(new Chanson(titre, artiste, duree, contenu, Genres.valueOf(genre)));
		IntLogger sfl = SingletonFileLogger.getInstance();
		sfl.write(Levels.INFO, "jMusicHub.addMusic() : success");
		System.out.println("Votre nouvelle musique a été ajoutée avec succès.\n" + elements.peekLast());
		return;
	}

	/**
	 * Removes a song from the ElementList, AlbumsList and PlaylistList.
	 * 
	 * @param elements the ElementList to save
	 * @param albums the AlbumList to save
	 * @param playlists the Playlist to save
	 */
	public void delMusic(ElementList elements, AlbumList albums, PlaylistList playlists){
		System.out.println(elements.listeChanson());

        System.out.println("Quelle chanson souhaitez-vous supprimer ?");
        System.out.println("Entrez le titre de la musique suivi de son artiste : Titre - Artiste");
        System.out.println("Cela supprimera la chanson de tous les albums et playlists où elle se trouve.");
        userInput = userInputObj.nextLine();

        LinkedList<Element> tempElement = new LinkedList<Element>(elements.getList());

		for(Element e : tempElement) {
			if(e instanceof Chanson) {
				Chanson chanson = (Chanson) e;
				String supp = chanson.getTitre() + " - " + chanson.getArtiste();
    			if(supp.equals(userInput)) {
    				elements.delChanson(chanson);
					IntLogger sfl = SingletonFileLogger.getInstance();
					sfl.write(Levels.INFO, "jMusicHub.delMusic() : " + userInput + " deleted form library");
    				System.out.println("Chanson supprimée avec succès de la bibliothèque.");
    			}
			}
		}

		for(Album a : albums.getList()) {

			LinkedList<Chanson> tempChanson = new LinkedList<Chanson>(a.getSongList());

    		for(Chanson c : tempChanson) {
    			String supp = c.getTitre() + " - " + c.getArtiste();
    			if(supp.equals(userInput)) {
    				a.del(c);
					IntLogger sfl = SingletonFileLogger.getInstance();
					sfl.write(Levels.INFO, "jMusicHub.delMusic() : " + userInput + " deleted form album");
    				System.out.println("Chanson supprimée avec succès de l'album.");
    			}
    		}
		}

		for(Playlist p : playlists.getList()) {

			tempElement = new LinkedList<Element>(p.getElementList());

    		for(Element e : tempElement) {
    			if(e instanceof Chanson) {
        			Chanson ch = (Chanson) e;
        			String supp = ch.getTitre() + " - " + ch.getArtiste();
        			if(supp.equals(userInput)) {
        				p.delChanson(ch);
						IntLogger sfl = SingletonFileLogger.getInstance();
						sfl.write(Levels.INFO, "jMusicHub.delMusic() : " + userInput + " deleted form playlist");
        				System.out.println("Chanson supprimée avec succès de la playlist.");
        			}
        		}
    		}
		}
	}

	/**
	 * Adds an audio book to the ElementList and displays it.
	 * 
	 * @param elements the ElementList to modify
	 */
	public void addLivre(ElementList elements){
		System.out.println("~~~Nouveau livre audio~~~");
		System.out.println("Entrez la commande QUIT à n'importe quel moment pour annuler l'ajout du nouveau livre audio.");

		String titre = newTitre();
		if (ifWantToQuit(titre)){
			return;
		}

		String auteur = newAuteur();
		if (ifWantToQuit(titre)){
			return;
		}

		int duree = newDuree();
		if (ifWantToQuit(String.valueOf(duree))){
			return;
		}

		String contenu = newContenu();
		if (ifWantToQuit(contenu)){
			return;
		}

		String langue = null;
		Boolean wrongValue = true;
		while(wrongValue) {
			try {
				langue = newLangue();
				wrongValue = false;
				if (ifWantToQuit(langue)){
					return;
				}
			} catch(WrongEnumValue ex) {
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.ERROR, "jMusicHub.addLivre() : user input is not a valid Langue");
				System.err.println(ex.getMessage());
			}
		}

		String categorie = null;
		wrongValue = true;
		while(wrongValue) {
			try {
				categorie = newCategorie();
				wrongValue = false;
				if (ifWantToQuit(categorie)){
					return;
				}
			} catch(WrongEnumValue ex) {
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.ERROR, "jMusicHub.addLivre() : user input is not a valid Categorie");
				System.err.println(ex.getMessage());
			}
		}

		elements.add(new LivreAudio(titre, auteur, duree, contenu, Langues.valueOf(langue), Categories.valueOf(categorie)));
		IntLogger sfl = SingletonFileLogger.getInstance();
		sfl.write(Levels.INFO, "jMusicHub.addLivre() : success");
		System.out.println("Votre nouveau livre audio a été ajouté avec succès.\n" + elements.peekLast());
	}

	/**
	 * Removes an audio book from the ElementList and PlaylistList.
	 * 
	 * @param elements the ElementList to modify
	 * @param playlists the Playlist to save
	 */
	public void delLivre(ElementList elements, PlaylistList playlists){
		System.out.println(elements.listeLivreAudio());

        System.out.println("Quelle livre audio souhaitez-vous supprimer ?");
        System.out.println("Entrez le titre du livre audio suivi de son auteur : Titre - Auteur");
        System.out.println("Cela supprimera le livre audio de toutes les playlists où il se trouve.");
        userInput = userInputObj.nextLine();

        LinkedList<Element> tempElement = new LinkedList<Element>(elements.getList());

		for(Element e : tempElement) {
			if(e instanceof LivreAudio) {
				LivreAudio livre = (LivreAudio) e;
        		String supp = livre.getTitre() + " - " + livre.getAuteur();
    			if(supp.equals(userInput)) {
    				elements.delLivreAudio(livre);
					IntLogger sfl = SingletonFileLogger.getInstance();
					sfl.write(Levels.INFO, "jMusicHub.delLivre() : " + userInput + " deleted form library");
    				System.out.println("Livre audio supprimé avec succès de la bibliothèque.");
    			}
    		}
    	}

		for(Playlist p : playlists.getList()) {

			tempElement = new LinkedList<Element>(p.getElementList());

    		for(Element e : tempElement) {
    			if(e instanceof LivreAudio) {
        			LivreAudio livre = (LivreAudio) e;
        			String supp = livre.getTitre() + " - " + livre.getAuteur();
        			if(supp.equals(userInput)) {
        				p.delLivreAudio(livre);
						IntLogger sfl = SingletonFileLogger.getInstance();
						sfl.write(Levels.INFO, "jMusicHub.delLivre() : " + userInput + " deleted form playlist");
        				System.out.println("Livre audio supprimé avec succès de la playlist.");
        			}
        		}
    		}
		}
	}

	/**
	 * Adds an album to the AlbumList and displays it.
	 * 
	 * @param albums the AlbumList to modify
	 */
	public void addAlbum(AlbumList albums){
		System.out.println("~~~Nouvel Album~~~");
		System.out.println("Entrez la commande QUIT à n'importe quel moment pour annuler l'ajout d'un nouvel album.");

		String titre = newTitre();
		if (ifWantToQuit(titre)){
			return;
		}

		String artiste = newArtiste();
		if (ifWantToQuit(artiste)){
			return;
		}

		String date = newDate();
		if (ifWantToQuit(date)){
			return;
		}

	
		albums.add(new Album(titre, artiste, date));
		IntLogger sfl = SingletonFileLogger.getInstance();
		sfl.write(Levels.INFO, "jMusicHub.addAlbum() : success");
		System.out.println("Votre nouvel album a été ajouté avec succès.\n" + albums.peekLast());
		return;
	}

	/**
	 * Removes an album from the AlbumList.
	 * 
	 * @param albums the AlbumList to modify
	 */
	public void delAlbum(AlbumList albums){
        System.out.println(albums + "\nQuel album souhaitez-vous supprimer ?");
        userInput = userInputObj.nextLine();
		for(Album a : albums.getList()) {
			if(a.getTitre().equals(userInput)) {
				albums.del(a);
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.INFO, "jMusicHub.delAlbum() : " + userInput + " deleted");
				System.out.println("Album supprimé avec succès de la bibliothèque.");
			}
		}
	}

	/**
	 * Adds a playlist to the PlaylistList and displays it.
	 * 
	 * @param playlists the PlaylistList to modify
	 */
	public void addPlaylist(PlaylistList playlists){
		System.out.println("~~~Nouvelle playlist~~~");
		System.out.println("Entrez la commande QUIT à n'importe quel moment pour annuler l'ajout de la nouvelle playlist.");
		String nom = newNom();
		if (ifWantToQuit(nom)){
			return;
		}
		playlists.add(new Playlist(nom));
		IntLogger sfl = SingletonFileLogger.getInstance();
		sfl.write(Levels.INFO, "jMusicHub.addPlaylist() : success");
		System.out.println("Votre nouvelle playlist a été ajoutée avec succès.\n" + playlists.peekLast());
		return;
	}

	/**
	 * Removes a playlist from the PlaylistList.
	 * 
	 * @param playlists the PlaylistList to modify
	 */
	public void delPlaylist(PlaylistList playlists){
        System.out.println(playlists + "\nQuelle playlist souhaitez-vous supprimer ?");
        userInput = userInputObj.nextLine();
		for(Playlist p : playlists.getList()) {
			if(p.getNom().equals(userInput)) {
				playlists.del(p);
				IntLogger sfl = SingletonFileLogger.getInstance();
				sfl.write(Levels.INFO, "jMusicHub.delPlaylist() : " + userInput + " deleted");
				System.out.println("Playlist supprimée avec succès de la bibliothèque.");
			}
		}
	}

	/**
	 * Displays the help menu for all commands.
	 */
	public void help() {
		System.out.println("L'application jMusicHub accepte les commandes suivantes :");
		System.out.println("\n					Contenu de la bibliothèque");
		System.out.println("	B  : affiche la bibliothèque musicale.");
		System.out.println("	L  : affiche les livres audio rangés par auteur.");
		System.out.println("	A  : affiche les albums rangés par date de sortie.");
		System.out.println("	C  : affiche les chansons d'un album.");					
		System.out.println("	G  : affiche les chansons rangées par genre d'un album.");
		System.out.println("	GA : affiche les chansons en ordre aléatoire d'un album.");
		System.out.println("	P  : affiche les playlists.");
		System.out.println("	M  : affiche les éléments d'une playlist.");
		System.out.println("	MA : affiche les éléments en ordre aléatoire d'une playlist.");

		System.out.println("\n					Modification de la bibliothèque");
		System.out.println("	b  : rajout d'une nouvelle chanson.");
		System.out.println("	l  : rajout d'un nouveau livre audio.");
		System.out.println("	a  : rajout d'un nouvel album.");
		System.out.println("	c  : rajout d'une chanson existante à un album.");
		System.out.println("	p  : rajout d'une nouvelle playlist.");
		System.out.println("	m  : rajout d'un élément existant à une playlist.");
		System.out.println();
		System.out.println("	-b : suppression d'une chanson de la bibliothèque. Supprime aussi l'élément dans les albums et les playlists.");
		System.out.println("	-l : suppression d'un livre audio. Supprime aussi l'élément dans les playlists.");
		System.out.println("	-a : suppression d'un album.");
		System.out.println("	-c : suppression d'une chanson d'un album.");
		System.out.println("	-p : suppression d'une playlist.");
		System.out.println("	-m : suppression d'un élément d'une playlist.");

		System.out.println("\n						Utilitaires");
		System.out.println("	s  : sauvegarde des playlists, des albums, des chansons et des livres audio.");
		System.out.println("	q  : quitter l'application.");
	}

	/**
	 * Exit jMusicHub after confirmation.
	 * 
	 * @return the boolean to continue or exit jMusicHub 
	 */
	public Boolean quit() {
		System.out.println("Souhaitez-vous quitter l'application ? [O/N]");
		userInput = userInputObj.nextLine().toLowerCase();
		Boolean run = true;

		if(userInput.equals("o") || userInput.equals("oui") || userInput.equals("y") || userInput.equals("yes")) {
			run = false;
			System.out.println("Bye bye !");
		}
		return run;
	}
}