/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;


/**
 System.out.println(vuelta.getIconHeight()+" uig
 "+vuelta.getIconWidth());vuelta.getIconHeight();
 212 uig 134
 @author Guille
 */
public class Carta extends JLabel {

    private ImageIcon aux, vuelta;
    private String url;
    private final Border borde = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.blue, 2), BorderFactory.createLoweredBevelBorder());
    final int WIDTH_DEF, HEIHT_DEF;
    private final int ANCHOMAX = 100, ALTOMAX = 100;
    int altura, ancho;
    private boolean sale;
    private Timer timer;
    private boolean bloquear;



    public Carta(String url) {
        bloquear=false;
        this.setBorder(borde);
        this.url = url;
        sale=false;
        vuelta = new ImageIcon("src/img/cartas/vuelta.png");//cambiarTamano(new ImageIcon("src/img/cartas/vuelta.png"),100,300);
        this.setSize(vuelta.getIconWidth(),vuelta.getIconHeight());
        WIDTH_DEF=vuelta.getIconWidth();
        HEIHT_DEF=vuelta.getIconHeight();
      //  this.setMinimumSize(new Dimension(,));
        //carta=cambiarTamano(new ImageIcon("src/img/cartas/vuelta.png"),100,100);
        this.setIcon(vuelta);
        // aux=cambiarTamano(new ImageIcon("src/img/logotrini.png"),100,100);
        aux = cambiarTamano(new ImageIcon(url), vuelta.getIconWidth(), vuelta.getIconHeight());
        // this.setOpaque(false);
    }


    public void paint(Graphics g) {
        //imagen de fondo, la carta
        g.drawImage(aux.getImage(), 1, 1, null);
        super.paint(g);
    }
    
    public void animar(){
        if(!bloquear){
            if(!sale){
                animarSalir();
            }else{
                animarEntrar();
            }
        }
    }

    public void animarSalir() {
        //System.out.println("icono=" + vuelta.getIconHeight() + " " + vuelta.getIconWidth());
       // System.out.println("contenedor=" + this.getHeight() + " " + this.getWidth());
        // this.setMaximumSize(new Dimension(this.getWidth(), getHeight()));
        altura = vuelta.getIconHeight();
        ancho = vuelta.getIconWidth();
        sale = true;
        empezar();
    }


    public void animarEntrar() {
        this.setIcon(cambiarTamano((ImageIcon) vuelta, 2, 2));
        sale = false;
        empezar();
    }


    public void empezar() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sale) {
                    altura--;
                    ancho--;
                } else {
                    altura++;
                    ancho++;
                }
                ponerImagen();
            }
        });
        timer.start();
        timer.setRepeats(true);
    }


    /**
     Le asigna la imagen a las cartas
     */
    public void ponerImagen() {
        //System.out.println(altura + " " + ancho);
        if (altura > 0 && ancho > 0 && altura < vuelta.getIconHeight() && ancho < vuelta.getIconWidth()) {
        //    System.out.println("poniendo img");
            this.setIcon(cambiarTamano(new ImageIcon("src/img/cartas/vuelta.png"), ancho, altura));
        } else {
            if (sale) {
                setIcon(null);
            } else {
                setIcon(vuelta);
            }
            timer.stop();
        }
        this.updateUI();
    }


    public void actualizarTamaño() {
        // if(!sale){
       // System.out.println("acctualiz" + this.getWidth() + "----" + this.getHeight());
//            carta=cambiarTamano(new ImageIcon(url), this.getWidth(), this.getHeight());
//            this.setIcon(carta);
        // }
    }

    public boolean isSale() {
        if(bloquear){//bloquear variable que desactiva el giro de la carta de nuevo 
            return false;
        }
        return sale;//=true si no esta
    }

    public void bloquear() {
        this.setIcon(cambiarTamano(aux,WIDTH_DEF,HEIHT_DEF));
        bloquear=true;
    }

    public String getUrl() {
        return url;
    }

    /*public static int getActivadas() {
        return activadas;
    }*/


    //ocultar
    //es igual
    /**
     Modifica el tamaño de las imagenes

     @param icono       se pasa por parametro el icono a reescalar
     @param anchoImagen establece el ancho de la imagen
     @param altoImagen  establece el alto de la imagen
     @return devuelve un ImageIcon que se asigna ya reescalado al original
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }
}
