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
    private final int BUFFER_SIZE=64*1024;  // 64 KB *1024
    
    /**
     * Asignamos la ruta del sonido
     * @param sonido 
     */
    public Sonido(String sonido){
        this.sonido=sonido;
    }
    /**
     * Creamos un hilo que repoduza la ruta del sonindo
     */
    @Override
    public void run(){
        repSonido();
    }
    /**
     * Metodo encargado de la reproduccion del hilo
     */
    private void repSonido(){
        try {
           SourceDataLine soundLine = null;//lo creamos a nulo para que si no encuenta la ruta no de error
           URL url= this.getClass().getResource(sonido); //obtenemos la url del sonido
           AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);//introduciomos en un AudioInputStream la uta para poder reproducitlo
           AudioFormat audioFormat = audioInputStream.getFormat();//obtenemos el formato del audio , con el tamaño
           DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);//obtenemos la informacio, adjuntandole el tamano minimo del buffer interno
           soundLine = (SourceDataLine) AudioSystem.getLine(info);//ingresaos el sonido para poderlo reproducirlo
           soundLine.open(audioFormat);//abribos el sondido con el formato del audio correspondiente
           soundLine.start();//iniciamos el sonido, lo empezamos a repoducir
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
