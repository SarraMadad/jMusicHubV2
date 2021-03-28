package musichub.util;

/**
 * Singleton class to display a message to the user.
 *
 * @author Sylvain BUI, Srikanth COLLATY, Maxence LECLERC, Sarra MADAD
 * @version 1.0
 * @see IntLogger
 * @see Levels
 */
public class SingletonConsoleLogger implements IntLogger {
    /** Single instance of the Singleton */
    private static SingletonConsoleLogger logger = null;

    /** private constructor to avoid client applications to use constructor */
    private SingletonConsoleLogger() {}

    /** static block initialization.
     *
     * @return the Singleton of the program
     */
    public static synchronized SingletonConsoleLogger getInstance() {
        if(logger == null) {
            logger = new SingletonConsoleLogger();
        }
        return logger;
    }

    /** Overridden method from the interface. Used by other classes.
     * Uses writeAtTerminal() method.
     *
     * @see #writeAtTerminal(Levels, String)
     * @param l the level of the message
     * @param message the message to write
     */
    public void write(Levels l, String message) {
        writeAtTerminal(l, message);
    }

    /** Private method to write on the console.
     * Called by write() method.
     *
     * @see #write(Levels, String)
     * @param l the level of the message
     * @param message the message to write
     */
    private void writeAtTerminal(Levels l, String message) {
        System.out.println ( l + " : " + message);
    }
}
