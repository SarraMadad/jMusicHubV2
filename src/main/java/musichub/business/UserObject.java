package musichub.business;

import java.io.Serializable;

/**
 * UserObject is a class that contain several information about the user command
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0
 */


public class UserObject implements Serializable {
    /** User command */
    private String command;
    /** Last user command */
    private String lastCommand;
    /** Server response */
    private String response;
    /** Music data */
    private Music music = new Music();

    /**
     * Default constructor
     * Initializes attributes
     */
    public UserObject() {
        command = "";
        lastCommand = "";
        response = "";
    }

    /**
     * get the user command
     *
     * @return retrieve the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * set the user command
     *
     * @param command set a assigning command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * get the user last command
     *
     * @return retrive the last command
     */
    public String getLastCommand() {
        return lastCommand;
    }

    /**
     * set the last command
     *
     * @param lastCommand set the last assigning command
     */
    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * get the server response
     *
     * @return retrive the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * set the server response
     *
     * @param response set a assigning response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * get the music
     *
     * @return retrieve a Music
     */
    public Music getMusic() {
        return music;
    }

    /**
     * set the music
     *
     * @param music set a assigning music
     */
    public void setMusic(Music music) {
        this.music = music;
    }
}
