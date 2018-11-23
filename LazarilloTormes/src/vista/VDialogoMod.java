/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrDialogoMod;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    private GridBagConstraints constrain;
    private JLabel labelDesarrolladores, labelNombreGuille, labelNombreJesus, labelLogoTrini;

    private ImageIcon flecha = new ImageIcon(this.getClass().getResource("/img/atras.png"));
    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.gif"));
    private ImageIcon iconoTrinitarias = new ImageIcon(this.getClass().getResource("/img/logotrini.png"));


    public VDialogoMod(Logica logica) {
        controlador = new ContrDialogoMod(this, logica);
    }


    public void generar() {
        setOpaque(false);
        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        defectoConstrain();

        crearLableDesarrolladores();
        crearLableNombreJesus();
        crearLableNombreGuille();
        crearLogoTrini();
        generarBoton();

        anadirControladores();
    }


    public void crearLableDesarrolladores() {
        labelDesarrolladores = new JLabel("Desarrolladores");
        labelDesarrolladores.setFont(bakerville(70));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 5;

        constrain.gridy = 0;
        constrain.gridx = 1;
        add(labelDesarrolladores, constrain);
        defectoConstrain();
    }


    public void crearLableNombreGuille() {
        labelNombreGuille = new JLabel("Guillermo Manso García");
        labelNombreGuille.setFont(bakerville(30));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 2;

        constrain.gridx = 2;
        constrain.gridy = 1;

        add(labelNombreGuille, constrain);
        defectoConstrain();
    }


    public void crearLableNombreJesus() {
        labelNombreJesus = new JLabel("Jesús Roncero García");
        labelNombreJesus.setFont(bakerville(30));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 2;

        constrain.gridy = 2;
        constrain.gridx = 2;
        add(labelNombreJesus, constrain);
        defectoConstrain();
    }


    private void crearLogoTrini() {
        labelLogoTrini = new JLabel(iconoTrinitarias);

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 4;

        constrain.gridx = 4;       //HORIZONTAL
        constrain.gridy = 4;        //VERTICAL

        add(labelLogoTrini, constrain);
        defectoConstrain();
    }


    private void generarBoton() {
        botonFlechaAtras = new JButton();
//        flecha = metodo.imagenEspejo("/img/flecha.png");

        constrain.fill = GridBagConstraints.BASELINE;

        constrain.gridy = 4;
        constrain.gridx = 0;
        add(botonFlechaAtras, constrain);

        defectoConstrain();

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

        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
        repaint();
    }


    public void anadirControladores() {
//        controlador = new ContrDialogoMod(this);
    }


    /*
     * Añade los elementos a la vista
     *
     * loc.ipadx = 50;
     * loc.anchor = GridBagConstraints.LINE_START;
     */
    public void defectoConstrain() {
        /*
         * POR DEFECTO
         */
        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weighty = 1.0; //para que se estiren las columnas
        constrain.weightx = 1.0; // El área de texto ocupa 1 filasa
        constrain.fill = GridBagConstraints.BOTH;
        constrain.gridwidth = 1;
        constrain.insets = new Insets(0, 0, 0, 0);
    }


    /**
     * Crea una fuente para aplicarla a los label
     * @param tamanofuente int que hay que pasar para asignar el tamaño a la
     *                     fuente
     * @return font Devuelve una fuente
     */
    public Font bakerville(int tamanofuente) {
        Font fuente = new Font("Baskerville Old Face", Font.BOLD, tamanofuente);
        return fuente;
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
