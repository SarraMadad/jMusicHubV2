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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void delMusic() {
        this.music.setData(null);
    }
}
