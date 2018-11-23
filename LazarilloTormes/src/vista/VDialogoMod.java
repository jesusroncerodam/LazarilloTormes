/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrDialogoMod;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabajodi.Logica;
import trabajodi.Metodos;


/**
 *
 * @author Guille
 */
public class VDialogoMod extends JPanel {

    private ContrDialogoMod controlador;
    private JButton botonFlechaAtras;
    private ImageIcon flecha = new ImageIcon(this.getClass().getResource("/img/atras.png"));
    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.jpg"));
    private GridBagConstraints constrain;
    private JLabel labelNombreGuille, labelNombreJesus;


    public VDialogoMod(Logica logica) {
        controlador = new ContrDialogoMod(this, logica);
    }


    public void generar() {

        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        generarBoton();
    }


    public void crearLableNombreJesus() {
        labelNombreJesus = new JLabel("Jesús Roncero García");

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 3;

        constrain.gridy = 1;
        constrain.gridx = 0;
        add(labelNombreJesus, constrain);
    }


    private void generarBoton() {
        botonFlechaAtras = new JButton();
//        flecha = metodo.imagenEspejo("/img/flecha.png");

        constrain.fill = GridBagConstraints.BASELINE;

        constrain.gridy = 0;
        constrain.gridx = 4;
        add(botonFlechaAtras, constrain);

        botonFlechaAtras.setIcon(flecha);
        botonFlechaAtras.setContentAreaFilled(false);
        botonFlechaAtras.setBorderPainted(false);
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
