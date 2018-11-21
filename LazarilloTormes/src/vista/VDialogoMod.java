/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrDialogoMod;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import trabajodi.Logica;
import trabajodi.Metodos;


/**
 *
 * @author Guille
 */
public class VDialogoMod extends JPanel {

    private ContrDialogoMod controlador;
    private JButton atras;
    private ImageIcon flecha;
    private Metodos metodo;

    ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoregistro.jpg"));


    public VDialogoMod(Logica logica) {
        controlador = new ContrDialogoMod(this, logica);
    }


    public void generar() {

        generarBoton();
    }


    private void generarBoton() {
        atras = new JButton();
//        flecha = metodo.imagenEspejo("/img/flecha.png");

        atras.setIcon(flecha);
        atras.setContentAreaFilled(false);
        atras.setBorderPainted(false);
        this.add(atras);
    }


    /**
     * El null se utiliza ya que el ImageObserver se utiliza para comprobar si
     * la imagen ha cambiado, cosa que en este caso no se necesita
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

}

//
// BufferedImage imagee;
//    int grados = 0;
//
//
//    @Override
//    public void paint(Graphics g) {
//
//        AffineTransform affineTransform = new AffineTransform();
//        //rotate the image by 45 degrees 
//        affineTransform.rotate(Math.toRadians(grados), 100, 100);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(flecha.getImage(), affineTransform, null);
//        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
//
//        super.paint(g2d); //To change body of generated methods, choose Tools | Templates.
//    }
//   Timer timer;
//
//
//    public void girar() {
//        timer = new Timer(500, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                grados += 10;
//                System.out.println("e");
//                repaint();
//            }
//        });
//        timer.start();
//        timer.setRepeats(true);
//    }
