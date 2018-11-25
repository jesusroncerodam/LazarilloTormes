package trabajodi;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Guille
 * @author Jesus Roncero
 */
public class Sonido extends Thread{
    private String sonido;
    
    /**
     * Asignamos la ruta del sonido
     * @param sonido 
     */
    public Sonido(String sonido){
        this.sonido=sonido;
    }
    public void run(){
        repSonido(sonido);
    }
    
    public void repSonido(String sonido){
        int BUFFER_SIZE = 64*1024;  // 64 KB
        try {
           SourceDataLine soundLine = null;
           URL url= this.getClass().getResource(sonido);
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
           AudioFormat audioFormat = audioInputStream.getFormat();
           DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
           soundLine = (SourceDataLine) AudioSystem.getLine(info);
           soundLine.open(audioFormat);
           soundLine.start();
           int nBytesRead = 0;
           byte[] sampledData = new byte[BUFFER_SIZE];
           while (nBytesRead != -1) {
              nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
              if (nBytesRead >= 0) {
                 // Writes audio data to the mixer via this source data line.
                 soundLine.write(sampledData, 0, nBytesRead);
              }
           }
           soundLine.drain();
           soundLine.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }

}
