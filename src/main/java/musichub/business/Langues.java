package musichub.business;

/**
 * Langues is an enum used to attribute a language to an audio book.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see LivreAudio
 */
public enum Langues {
	/** Sets the language to French. */
	FRANCAIS("Français"),
	/** Sets the language to English. */
	ANGLAIS("Anglais"),
	/** Sets the language to Italian. */
	ITALIEN("Italien"),
	/** Sets the language to Spanish. */
	ESPAGNOL("Espagnol"),
	/** Sets the language to Dutch. */
	ALLEMAND("Allemand");

	/** String value of the enum. */
	private String langue;

	/**
	 * Constructor when a language is set to a new audio book.
	 * 
	 * @param langue language of this new audio book
	 */
	private Langues(String langue) {
		this.langue = langue;
	}

	/**
	 * Returns the String value of this language.
	 * <p>
	 * The language is displayed in the librairy.
	 * 
	 * @return the language of this enum value
	 */
	public String getLangue() {
		return langue;
	}
}