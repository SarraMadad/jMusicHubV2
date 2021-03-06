package musichub.business;

import java.lang.Exception;

/**
 * WrongEnumValue is an exception created when the value entered by the
 * user is not in the enum list.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see Genres
 * @see Langues
 * @see Categories
 */
public class WrongEnumValue extends Exception {

	/**
	 * Returns the exception message.
	 * 
	 * @param message the message to display
	 */
	public WrongEnumValue(String message) {
		super(message);
	}
}