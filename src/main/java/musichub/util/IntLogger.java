package musichub.util;

/**
 * IntLogger contains the write() method for SingletonFileLogger
 * and SingletonConsoleLogger.
 *
 * @author Sylvain BUI, Srikanth COLLATY, Maxence LECLERC, Sarra MADAD
 * @version 1.0
 * @see SingletonFileLogger
 * @see SingletonConsoleLogger
 * @see Levels
 */
public interface IntLogger {

    /**
     * Writes in the logfile or the console, depending of the class utilising it.
     *
     * @param l the level of the message
     * @param message the message to write
     */
    public void write(Levels l, String message);
}
