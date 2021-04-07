package musichub.business;

/**
 * LivreAudio is an audio book object in the librairy.
 * <p>
 * An audio book can be added and removed from the librairy 
 * or a Playlist object.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Element
 * @see ElementList
 * @see Playlist
 */
public class LivreAudio extends Element {
	/** Writter of the audio book. */
	private String auteur;
	/**
	 * Language of the audio book.
	 * @see Langues
	 */
	private Langues langue;
	/**
	 * Category of the audio book.
	 * @see Categories
	 */
	private Categories categorie;

	/**
	 * Sole constructor. (For invocation by subclass 
	 * constructors, typically implicit.)
	 */
	public LivreAudio() {
		this.id = compteurId++;
	}

	/**
	 * Complete constructor for an audio book.
	 * 
	 * @param titre title of this new audio book
	 * @param auteur writter of this new audio book
	 * @param duree length of this new audio book
	 * @param contenu location of this new audio book
	 * @param langue language of this new audio book
	 * @param categorie category of this new audio book
	 */
	public LivreAudio(String titre, String auteur, int duree, String contenu, Langues langue, Categories categorie) {
		this.id = compteurId++;
		this.titre = titre;
		this.auteur = auteur;
		this.duree = duree;
		this.contenu = contenu;
		this.langue = langue;
		this.categorie = categorie;
	}

	/**
	 * Overrides the standard toString method.
	 * <p>
	 * Used by Playlist objects.
	 * 
	 * @return all informations about this audio book
	 * @see #getAuteur()
	 * @see #getLangue()
	 * @see #getCategorie()
	 * @see Element#getTitre()
	 * @see Element#getDuree()
	 * @see Element#getContenu()
	 * @see Album#getList()
	 * @see Playlist#getList()
	 */
	public String toString() {
		return "Titre : "+getTitre()+" - Auteur : "+getAuteur()+" - Duree : "+getDuree()+"s - Contenu : "+getContenu()+" - Langue : "+getLangue()+" - Catégorie : "+getCategorie();
	}

	/** 
	 * Returns the writter of this audio book.
	 * <p>
	 * The writter of this element is displayed in the librairy.
	 * 
	 * @return the writter of this audio book
	 */
	public String getAuteur() {
		return auteur;
	}

	/** 
	 * Registers the writter of this audio book when deserialized.
	 * 
	 * @param auteur the writter of this serialized object
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/** 
	 * Returns the language of this audio book.
	 * <p>
	 * The language of this element is displayed in the librairy.
	 * 
	 * @return the language of this audio book
	 */
	public Langues getLangue() {
		return langue;
	}

	/** 
	 * Registers the language of this audio book when deserialized.
	 * 
	 * @param langue the language of this serialized object
	 */
	public void setLangue(Langues langue) {
		this.langue = langue;
	}

	/** 
	 * Returns the category of this audio book.
	 * <p>
	 * The category of this element is displayed in the librairy.
	 * 
	 * @return the category of this audio book
	 */
	public Categories getCategorie() {
		return categorie;
	}

	/** 
	 * Registers the category of this audio book when deserialized.
	 * 
	 * @param categorie the category of this serialized object
	 */
	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

}