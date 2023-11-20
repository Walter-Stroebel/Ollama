package nl.infcomtec.ollama;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * Converts text to image if text is valid SVG.
 *
 * @author Walter Stroebel
 */
public class ModalitySVG extends Modality {

    public ModalitySVG(String currentText) {
        super(currentText);
    }

    @Override
    protected void convert() {

        // Run ImageMagick
        try {
            ProcessBuilder pb = new ProcessBuilder("convert", "svg:" + outputFile.getAbsolutePath(), pngOutputFile.getAbsolutePath());
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            Logger.getLogger(Ollama.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public SwingWorker<BufferedImage, String> getWorker() {
        return new SwingWorker<BufferedImage, String>() {
            @Override
            protected BufferedImage doInBackground() throws Exception {
                work();
                return image;
            }
        };
    }

}
