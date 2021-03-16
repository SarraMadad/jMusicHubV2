package musichub.business;

/**
 * Genres is an enum used to attribute a type to a song.
 * 
 * @author FERNANDES Mickael and LECLERC Maxence
 * @version 1.0
 * @see Chanson
 */
public enum Genres {
	/** Sets the type to "Jazz". */
	JAZZ("Jazz"),
	/** Sets the type to "Classique". */
	CLASSIQUE("Classique"),
	/** Sets the type to "Hip-Hop". */
	HIP_HOP("Hip-Hop"),
	/** Sets the type to "Rock". */
	ROCK("Rock"),
	/** Sets the type to "Pop". */
	POP("Pop"),
	/** Sets the type to "Rap". */
	RAP("Rap");

	/** String value of the enum. */
	private String genre;

	/**
	 * Constructor when a type is set to a new song.
	 * 
	 * @param genre type of this new song
	 */
	private Genres(String genre) {
		this.genre = genre;
	}

	/**
	 * Returns the String value of this category.
	 * <p>
	 * The type is displayed in the librairy.
	 * 
	 * @return the type of this enum value
	 */
	public String getGenre() {
		return genre;
	}
}