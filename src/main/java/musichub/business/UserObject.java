package musichub.business;

import javax.sound.sampled.AudioInputStream;
import java.io.Serializable;

public class UserObject implements Serializable {
    private String command;
    private String lastCommand;
    private String response;
    private AudioInputStream music;

    public UserObject() {
        command = "";
        lastCommand = "";
        response = "";
        music = null;
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

    public AudioInputStream getMusic() {
        return music;
    }

    public void setMusic(AudioInputStream music) {
        this.music = music;
    }
}
