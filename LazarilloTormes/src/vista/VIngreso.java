/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrIngreso;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabajodi.Logica;
import trabajodi.Metodos;


/*
 *
 * @author Guille
 *
 *
 */
public class VIngreso extends JPanel {

    private ContrIngreso controlador;
    private Metodos metodo = new Metodos();
    private JLabel labelNombre, labelTema, labelDificultad;
    private TextField campoNombre;
    private JButton botonFlechaAtrás, botonFlechaSiguiente;
    TextField nombre;
    JPanel tema1, tema2, tema3;
    JButton atras, adelante;

    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));
    private ImageIcon fondoRegistro = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.jpg"));
    private Checkbox facil, medio, dificil;
    /*
     *
     *
     *
     *
     *
     *
     *
     *
     */
    private GridBagConstraints constrain;
    private GridBagLayout gridLayout;


    /*
     *
     *
     */
    public VIngreso(Logica logica) {
        controlador = new ContrIngreso(this, logica);
    }


    public void generar() {
        this.setOpaque(false);

        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weighty = 0.6; //para que se estiren las columnas
        constrain.weightx = 0.6; // El área de texto ocupa 1 filasa

        constrain.fill = GridBagConstraints.BOTH;

        crearElementos();
        anadirElementos();
        estiloElementos();
        setVisible(true);
    }


    public void crearElementos() {

        labelNombre = new JLabel("Nombre: ");
        campoNombre = new TextField("Introduce tu nombre");

        labelTema = new JLabel("Tema");
        tema1 = new JPanel();
        tema2 = new JPanel();
        tema3 = new JPanel();

        labelDificultad = new JLabel("Dificultad");
        CheckboxGroup cbg = new CheckboxGroup();

        facil = new Checkbox("Facil", cbg, true);
        medio = new Checkbox("Medio", cbg, false);
        dificil = new Checkbox("Dificil", cbg, false);

        imagenFlecha = metodo.cambiarTamano(imagenFlecha, 50, 50);
        botonFlechaAtrás = new JButton(imagenFlecha);
        botonFlechaSiguiente = new JButton();
    }


    /*
     * Añade los elementos a la vista
     *
     *
     */
    public void anadirElementos() {

        constrain.gridx = 0;
        constrain.gridy = 0;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;
        add(labelNombre, constrain);

        constrain.gridx = 1;
        constrain.gridy = 0;
        constrain.fill = GridBagConstraints.NONE;
//        campoNombre.setPreferredSize(new Dimension(50, 50));
//        campoNombre.setSize(50, 50);
        constrain.insets = new Insets(50, 25, 25, 25);
        add(campoNombre, constrain);
        constrain.fill = GridBagConstraints.BOTH;
        constrain.insets = new Insets(0, 0, 0, 0);

        /*
         * COLCOAR TEMA
         */
        constrain.gridx = 0;
        constrain.gridy = 1;
        add(labelTema, constrain);

        constrain.gridx = 1;
        constrain.gridy = 1;
        add(tema1, constrain);

        constrain.gridx = 2;
        constrain.gridy = 1;
        add(tema2, constrain);

        constrain.gridx = 3;
        constrain.gridy = 1;
        add(tema3, constrain);

        /*
         * COLOCAR DIFICULTAD
         */
        constrain.gridx = 0;
        constrain.gridy = 2;
        add(labelDificultad, constrain);

        constrain.gridx = 1;
        constrain.gridy = 2;
        add(facil, constrain);

        constrain.gridx = 2;
        constrain.gridy = 2;
        add(medio, constrain);

        constrain.gridx = 3;
        constrain.gridy = 2;
        add(dificil, constrain);

        /*
         * BOTONES
         */
        constrain.gridx = 0;
        constrain.gridy = 3;
        add(botonFlechaAtrás, constrain);
        constrain.gridx = 3;
        constrain.gridy = 3;
        add(botonFlechaSiguiente, constrain);
    }


    /**
     * Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {
        tema1.setBackground(Color.red);
        tema2.setBackground(Color.darkGray);
        tema3.setBackground(Color.green);

//        botonFlechaAtrás.setIcon(metodo.cambiarTamano(imagenFlecha, botonFlechaAtrás.getWidth(), botonFlechaAtrás.getHeight()));
//        botonFlechaSiguiente.setIcon(metodo.cambiarTamano(imagenFlecha, botonFlechaSiguiente.getWidth(), botonFlechaSiguiente.getHeight()));
    }


    @Override
    public void paint(Graphics g) {

        g.drawImage(fondoRegistro.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
    }

//        constrain = new GridBagConstraints();
//        constrain.gridx = 1;
//        constrain.gridy = 1;
//        constrain.fill = GridBagConstraints.HORIZONTAL;
//        constrain.weighty = 0.5;
//        JLabel mainPanel = new JLabel(fondo) {
//            @Override
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
//                size.width = Math.max(size.width, lmPrefSize.width);
//                size.height = Math.max(size.height, lmPrefSize.height);
//                return size;
//            }
//        };
//        mainPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.weightx = 1.0;
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//
//        // Let's put a filler bottom component that will push the rest to the top
//        gbc.weighty = 1.0;
//        mainPanel.add(Box.createGlue(), gbc);
//        add(mainPanel);
//        pack();
}
