package musichub.business;

/**
 * Chanson is a song object in the librairy.
 * <p>
 * A song can be added and removed from the librairy,
 * an Album or a Playlist object.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Element
 * @see ElementList
 * @see Album
 * @see Playlist
 */
public class Chanson extends Element {
	/** Artist of the song. */
	private String artiste;
	/**
	 * Type of the song.
	 * @see Genres
	 */
	private Genres genre;

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public Chanson() {
		this.id = compteurId++;
	}

	/**
	 * Complete constructor for a song.
	 * 
	 * @param titre title of this new song
	 * @param artiste artist of this new song
	 * @param duree length of this new song
	 * @param contenu location of this new song
	 * @param genre type of this new song
	 */
	public Chanson(String titre, String artiste, int duree, String contenu, Genres genre) {
		this.id = compteurId++;
		this.titre = titre;
		this.artiste = artiste;
		this.duree = duree;
		this.contenu = contenu;
		this.genre = genre;
	}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used by Album and Playlist objects.
	 * 
	 * @return all informations about this song
	 * @see #getArtiste()
	 * @see #getGenre()
	 * @see Element#getTitre()
	 * @see Element#getDuree()
	 * @see Element#getContenu()
	 * @see Album#getList()
	 * @see Playlist#getList()
	 */
	public String toString() {
		return "Titre : "+getTitre()+" - Artiste : "+getArtiste()+" - Duree : "+getDuree()+"s - Contenu : "+getContenu()+" - Genre : "+getGenre();
	}

	/** 
	 * Returns the artist of this song.
	 * <p>
	 * The artist of this element is displayed in the librairy.
	 * 
	 * @return the artist of this song
	 */
	public String getArtiste() {
		return artiste;
	}

	/** 
	 * Registers the artist of this song when deserialized.
	 * 
	 * @param artiste the artist of this serialized object
	 */
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	/** 
	 * Returns the type of this song.
	 * <p>
	 * The type of this element is displayed in the librairy.
	 * 
	 * @return the type of this song
	 */
	public Genres getGenre() {
		return genre;
	}

	/** 
	 * Registers the type of this song when deserialized.
	 * 
	 * @param genre the type of this serialized object
	 */
	public void setGenre(Genres genre) {
		this.genre = genre;
	}
}