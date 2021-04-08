package musichub.business;

import java.io.Serializable;

/**

 *
 * @author Sylvain BUI, Maxence LECLERC, Nour-El-Houda LOUATY, Sarra MADAD
 * @version 1.0

 */


public class UserObject implements Serializable {
    private String command;
    private String lastCommand;
    private String response;
    private Music music = new Music();


    public UserObject() {
        command = "";
        lastCommand = "";
        response = "";
    }

    /**
     *
     * @return retrieve the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command set a assigning command
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return retrive the last command
     */
    public String getLastCommand() {
        return lastCommand;
    }

    /**
     * @param lastCommand set the last assigning command
     */
    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    /**
     * @return retrive the response
     */
    public String getResponse() {
        return response;
    }

    /**
     *
     * @param response set a assigning response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     *
     * @return retrieve a Music
     */
    public Music getMusic() {
        return music;
    }

    /**
     *
     * @param music set a assigning music
     */
    public void setMusic(Music music) {
        this.music = music;
    }

    /**
     * Delete the music
     */
    public void delMusic() {
        this.music.setData(null);
    }
}
