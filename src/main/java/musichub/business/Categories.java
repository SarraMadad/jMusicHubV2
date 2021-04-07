package musichub.business;

/**
 * Categorie is an enum used to attribute a category to an audio book.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see LivreAudio
 */
public enum Categories {
	/** Sets the category to "Jeunesse". */
	JEUNESSE("Jeunesse"),
	/** Sets the category to "Roman". */
	ROMAN("Roman"),
	/** Sets the category to "Théatre". */
	THEATRE("Théatre"),
	/** Sets the category to "Discours". */
	DISCOURS("Discours"),
	/** Sets the category to "Documentaire". */
	DOCUMENTAIRE("Documentaire");

	/** String value of the enum. */
	private String categorie;

	/**
	 * Constructor when a category is set to a new audio book.
	 * 
	 * @param categorie category of this new audio book
	 */
	private Categories(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Returns the String value of this category.
	 * <p>
	 * The category is displayed in the librairy.
	 * 
	 * @return the category of this enum value
	 */
	public String getCategorie() {
		return categorie;
	}
}