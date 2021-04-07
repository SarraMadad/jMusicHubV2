package musichub.business;

import java.util.*;

/**
 * An Album object contains a list of songs from the librairy.
 * <p>
 * An album can be added and removed from the librairy,
 * and can be edited to add or remove songs from its list.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see AlbumList
 * @see Element
 * @see Chanson
 */
public class Album {
	/** ID counter for all albums. */
	private static int compteurId = 0;
	/** ID of the album. */
	private int id;
	/** Title of the album. */
	private String titre;
	/** Artist of the album. */
	private String artiste;
	/** Release date of the album. */
	private String dateSortie;
	/** Length of all the songs in the album. */
	private int duree;
	/** List of the songs in the album. */
	private LinkedList<Chanson> songList = new LinkedList<Chanson>();

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public Album() {
		this.id = compteurId++;
	}

	/**
	 * Complete constructor for an album.
	 * 
	 * @param titre title of this new album
	 * @param artiste artist of this new album
	 * @param dateSortie release date of this new album
	 */
	public Album(String titre, String artiste, String dateSortie) {
		this.id = compteurId++;
		this.titre = titre;
		this.artiste = artiste;
		this.dateSortie = dateSortie;
	}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used for displaying the object.
	 * 
	 * @return all informations about this album
	 * @see #getTitre()
	 * @see #getArtiste()
	 * @see #getDuree()
	 * @see #getDateSortie()
	 */
	public String toString() {
		return "Titre : "+getTitre()+" - Artiste : "+getArtiste()+" - Duree : "+getDuree()+"s - Date de sortie : "+getDateSortie();
	}

	/** 
	 * Returns the ID of this album.
	 * 
	 * @return the ID number of this album
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Registers the ID of this album when deserialized.
	 * 
	 * @param id the ID number of this serialized object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * Returns the title of this album.
	 * <p>
	 * The title of this element is displayed in the librairy.
	 * 
	 * @return the title of this element
	 * @see #toString()
	 */
	public String getTitre() {
		return titre;
	}

	/** 
	 * Registers the title of this album when deserialized.
	 * 
	 * @param titre the title of this serialized object
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/** 
	 * Returns the artist of this album.
	 * <p>
	 * The artist of this element is displayed in the librairy.
	 * 
	 * @return the artist of this element
	 * @see #toString()
	 */
	public String getArtiste() {
		return artiste;
	}

	/** 
	 * Registers the artist of this album when deserialized.
	 * 
	 * @param artiste the artist of this serialized object
	 */
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	/**
	 * Retrieves the length of all songs in the album.
	 *<p>
	 * The length of this element is displayed in the librairy.
	 * 
	 * @return the length of this album
	 * @see Chanson#getDuree()
	 */
	public int getDuree() {
		duree = 0;

		for(Chanson c : this.songList) {
			duree += c.getDuree();
		}

		return duree;
	}

	/** 
	 * Registers the length of this album when deserialized.
	 * 
	 * @param duree the length of this serialized object
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/** 
	 * Returns the release date of this album.
	 * <p>
	 * The release date of this element is displayed in the librairy.
	 * 
	 * @return the release date of this element
	 * @see #toString()
	 */
	public String getDateSortie() {
		return dateSortie;
	}

	/** 
	 * Registers the release date of this album when deserialized.
	 * 
	 * @param dateSortie the release date of this serialized object
	 */
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	/** 
	 * Returns the song list of this album.
	 * 
	 * @return the song list of this element
	 * @see Chanson
	 */
	public LinkedList<Chanson> getSongList() {
		return songList;
	}

	/** 
	 * Registers the song list of this album when deserialized.
	 * 
	 * @param songList the song list of this serialized object
	 * @see Chanson
	 */
	public void setSongList(LinkedList<Chanson> songList) {
		this.songList = songList;
	}

	/**
	 * Adds a song to its song list.
	 * 
	 * @param c the song to add
	 * @see Chanson
	 */
	public void add(Chanson c) {
		songList.add(c);
	}

	/**
	 * Removes a song from its song list.
	 * 
	 * @param c the song to remove
	 * @see Chanson
	 */
	public void del(Chanson c) {
		songList.remove(c);
	}

	/**
	 * Displays its song list as a String without modifying the order.
	 * 
	 * @return the song list as a String
	 * @see Chanson#toString()
	 */
	public String getList() {
		String chansons = "\nChansons dans l'album :\n";
 
        for(Chanson c:getSongList()) {
			chansons += c.toString() + "\n";
		}

		return chansons;
	}

	/**
	 * Displays the input song list as a String.
	 * <p>
	 * Used for random lists and sorted lists.
	 * 
	 * @param list the song list to display
	 * @return the input song list as a String
	 * @see Chanson#toString()
	 */
	public String getList(LinkedList<Chanson> list) {
		String chansons = "\nChansons dans l'album :\n";
 
        for(Chanson c:list) {
			chansons += c.toString() + "\n";
		}

		return chansons;
	}

	/**
	 * Returns its song list in a random order.
	 * 
	 * @return the song list in a random order
	 */
	public LinkedList<Chanson> aleatoire() {
		LinkedList<Chanson> aleaList = new LinkedList<Chanson>(songList);
		Collections.shuffle(aleaList);
		return aleaList;
	}

}