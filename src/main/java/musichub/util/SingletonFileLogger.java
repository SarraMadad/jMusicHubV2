package musichub.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Singleton class to write a message in the logfile.
 *
 * @author Sylvain BUI, Srikanth COLLATY, Maxence LECLERC, Sarra MADAD
 * @version 1.0
 * @see IntLogger
 * @see Levels
 */
public class SingletonFileLogger implements IntLogger {
    /** Directory of the logfile */
    private static final String DIR = System.getProperty("user.dir");
    /** Name of the logfile */
    private static final String LOGS_FILE_PATH = DIR + "\\log.txt";
    /** Single instance of the Singleton */
    private static SingletonFileLogger logger = null;

    /** private constructor to avoid client applications to use constructor */
    private SingletonFileLogger() {}

    /** static block initialization.
     *
     * @return the Singleton of the program
     */
    public static synchronized SingletonFileLogger getInstance() {
        if(logger == null) {
            logger = new SingletonFileLogger();
        }

        return logger;
    }

    /** Overridden method from the interface. Used by other classes.
     * Uses writeToFile() method.
     *
     * @see #writeToFile(Levels, String, String)
     * @param l the level of the message
     * @param message the message to write
     */
    @Override
    public void write(Levels l, String message) {
        writeToFile(l, message, LOGS_FILE_PATH);
    }

    /** Private method to write into the logfile.
     * Called by write() method.
     *
     * @see #write(Levels, String)
     * @param l the level of the message
     * @param message the message to write
     * @param fileName the path to the logfile
     */
    private void writeToFile(Levels l, String message, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write("[" + new Timestamp(new Date().getTime()).toString() + "] - " + l + " - " + message);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
