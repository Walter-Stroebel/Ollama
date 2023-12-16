package nl.infcomtec.ollama;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Overall configuration.
 *
 * @author Walter Stroebel
 */
public class OllamaConfig {

    public int x;
    public int y;
    public int w;
    public int h; // of the chat window
    public float fontSize; // to keep things readable
    public String[] ollamas;
    public String lastEndpoint;
    public String lastModel;
    public String openAIKey;
    @JsonIgnore
    public boolean streaming;

    public void update(Rectangle bounds) {
        x = bounds.x;
        y = bounds.y;
        w = bounds.width;
        h = bounds.height;
        update();
    }

    public void update() {
        try {
            Ollama.getMapper().writeValue(Ollama.configFile, this);
        } catch (IOException ex) {
            Logger.getLogger(OllamaChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLastEndpoint() {
        return null == lastEndpoint ? null : lastEndpoint;
    }

    public String getLastModel() {
        return null == lastModel ? null : lastModel;
    }
}
