package musichub.business;

import java.util.*;

/**
 * A Playlist object contains a list of songs and audio books from the librairy.
 * <p>
 * A playlist can be added and removed from the librairy,
 * and can be edited to add or remove elements from its list.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see PlaylistList
 * @see Element
 * @see Chanson
 * @see LivreAudio
 */
public class Playlist {
	/** ID counter for all playlists. */
	private static int compteurId = 0;
	/** ID of the playlist. */
	private int id;
	/** Name of the playlist. */
	private String nom;
	/** List of the elements in the playlist. */
	private LinkedList<Element> elementList = new LinkedList<Element>();

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public Playlist() {
		this.id = compteurId++;
	}

	/**
	 * Complete constructor for a playlist.
	 * 
	 * @param nom name of this new playlist
	 */
	public Playlist(String nom) {
		this.id = compteurId++;
		this.nom = nom;
	}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used for displaying the object.
	 * 
	 * @return all informations about this playlist
	 * @see #getNom()
	 */
	public String toString() {
		return "Nom : "+getNom();
	}

	/** 
	 * Returns the ID of this playlist.
	 * 
	 * @return the ID number of this album
	 */
	public int getId() {
		return id;
	}

	/** 
	 * Registers the ID of this playlist when deserialized.
	 * 
	 * @param id the ID number of this serialized object
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * Returns the name of this playlist.
	 * <p>
	 * The name of this element is displayed in the librairy.
	 * 
	 * @return the name of this element
	 * @see #toString()
	 */
	public String getNom() {
		return nom;
	}

	/** 
	 * Registers the name of this playlist when deserialized.
	 * 
	 * @param nom the name of this serialized object
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** 
	 * Returns the elements list of this playlist.
	 * 
	 * @return the elements list of this element
	 * @see Element
	 */
	public LinkedList<Element> getElementList() {
		return elementList;
	}

	/** 
	 * Registers the elements list of this playlist when deserialized.
	 * 
	 * @param elementList the elements list of this serialized object
	 * @see Element
	 */
	public void setElementList(LinkedList<Element> elementList) {
		this.elementList = elementList;
	}

	/**
	 * Adds a song or an audio book to its elements list.
	 * 
	 * @param e the element to add
	 * @see Element
	 */
	public void add(Element e) {
		elementList.add(e);
	}

	/**
	 * Removes a song from its elements list.
	 * 
	 * @param c the song to remove
	 * @see Chanson
	 */
	public void delChanson(Chanson c) {
		elementList.remove(c);
	}

	/**
	 * Removes an audio book from its elements list.
	 * 
	 * @param l the audio book to remove
	 * @see LivreAudio
	 */
	public void delLivreAudio(LivreAudio l) {
		elementList.remove(l);
	}

	/**
	 * Displays its elements list as a String without modifying the order.
	 * 
	 * @return the elements list as a String
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 */
	public String getList() {
        String elements = "\nElements dans la playlist :\n";
 
        for(Element e:getElementList()) {
			elements += e.toString() + "\n";
		}

		return elements;
	}

	/**
	 * Displays the input elements list as a String.
	 * <p>
	 * Used for random lists.
	 * 
	 * @param aleaList the elements list to display
	 * @return the input elements list as a String
	 * @see Chanson#toString()
	 * @see LivreAudio#toString()
	 */
	public String getList(LinkedList<Element> aleaList) {
		String elements = "\nElements dans la playlist - mode aléatoire :\n";
 
        for(Element e:aleaList) {
			elements += e.toString() + "\n";
		}

		return elements;
	}

	/**
	 * Returns its element list in a random order.
	 * 
	 * @return the element list in a random order
	 */
	public LinkedList<Element> aleatoire() {
		LinkedList<Element> aleaList = new LinkedList<Element>(elementList);
		Collections.shuffle(aleaList);
		return aleaList;
	}
}