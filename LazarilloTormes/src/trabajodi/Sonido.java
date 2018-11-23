/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
 */
public class Sonido extends Thread{
    String mensaje;
    String sonido;
    int timeRep=0;
    boolean stop=false;
    public Sonido(){
        mensaje="Hola";
    }
    public Sonido(String sonido){
        this.sonido=sonido;
    }
    public Sonido(String sonido,boolean stop){
        this.sonido=sonido;
        stop=false;
    }
    public void run(){
        repSonido(sonido);
        System.out.println("Este proceso ha terminado:"+this.getName());
    }

    public void setMensaje(String msj){
        this.mensaje = msj;
    }
    
    public void repSonido(String sonido){
        SourceDataLine soundLine = null;
        int BUFFER_SIZE = 64*1024;  // 64 KB
        // Set up an audio input stream piped from the sound file.
        try {
            
         //InputStream path = getClass().getResourceAsStream("/sonidos/air.wav");
         URL url= this.getClass().getResource("/sonidos/air.wav");
         //   URL url = getClass().getResource("sonidos/air.wav");
           //File soundFile = new File(getClass().getResource("sonidos/air.wav"));//"src/sonidos/air.wav"path
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
        } finally {
           
        }
    }

}
