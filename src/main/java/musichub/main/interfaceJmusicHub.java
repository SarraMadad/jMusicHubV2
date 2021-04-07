package musichub.main;

import musichub.business.*;

/**
 * interfaceJmusicHub contains the prototypes of the methods 
 * for the creation of new songs, audio books, albums and
 * playists in jMusicHub.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see jMusicHub
 */
public interface interfaceJmusicHub {

	/**
	 * Returns the title entered by the user.
	 * 
	 * @return the title as a String
	 */
	public String newTitre();

	/**
	 * Returns the name entered by the user.
	 * 
	 * @return the name as a String
	 */
	public String newNom();

	/**
	 * Returns the artist entered by the user.
	 * 
	 * @return the artist as a String
	 */
	public String newArtiste();

	/**
	 * Returns the writter entered by the user.
	 * 
	 * @return the writter as a String
	 */
	public String newAuteur();

	/**
	 * Returns the length entered by the user. Checks if the value is a number.
	 * 
	 * @return the length as an int
	 */
	public int newDuree();

	/**
	 * Returns the location entered by the user.
	 * 
	 * @return the location as a String
	 */
	public String newContenu();

	/**
	 * Returns the category entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the category as a String
	 * @throws WrongEnumValue if an input exception occured
	 * @see musichub.business.WrongEnumValue
	 */
	public String newCategorie() throws WrongEnumValue;

	/**
	 * Returns the date entered by the user.
	 * 
	 * @return the date as a String
	 */
	public String newDate();

	/**
	 * Returns the language entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the language as a String
	 * @throws WrongEnumValue if an input exception occured
	 * @see musichub.business.WrongEnumValue
	 */
	public String newLangue() throws WrongEnumValue;

	/**
	 * Returns the type entered by the user. Checks if the value is in
	 * the enum.
	 * 
	 * @return the type as a String
	 * @throws WrongEnumValue if an input exception occured
	 * @see musichub.business.WrongEnumValue
	 */
	public String newGenre() throws WrongEnumValue;
}