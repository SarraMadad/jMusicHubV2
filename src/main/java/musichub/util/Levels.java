package musichub.util;

/**
 * Levels is an enum used to attribute a type to a message in the logs.
 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 * @see IntLogger
 * @see SingletonFileLogger
 * @see SingletonConsoleLogger
 */
public enum Levels {
    /** Sets the type to "ERROR". */
    ERROR("ERROR"),
    /** Sets the type to "WARNING". */
    WARNING("WARNING"),
    /** Sets the type to "INFO". */
    INFO("INFO"),
    /** Sets the type to "DEBUG". */
    DEBUG("DEBUG");

    /** String value of the enum. */
    private String levelValue;

    /**
     * Constructor when a level is set to a message.
     *
     * @param value type of the message
     */
    private Levels(String value) {
        this.levelValue = value;
    }

    /**
     * Returns the String value of this level.
     *
     * @return the type of this enum value
     */
    public String getLevel() {
        return levelValue;
    }
}
