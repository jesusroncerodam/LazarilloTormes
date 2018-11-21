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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabajodi.Logica;
import trabajodi.Metodos;


/**
 *
 * @author Guille
 */
public class VIngreso extends JPanel {

    private ContrIngreso controlador;
    private Metodos metodo;
    private JPanel panel1;
    private final int ANCHOMARCO = 500, ALTOMARCO = 500;
    private JLabel labelNombre, labelTema, labelDificultad;
    private TextField campoNombre;
    private JButton botonFlechaAtrás, avanzarPagina;
    TextField nombre;
    JPanel tema1, tema2, tema3;
    JButton atras, adelante;

    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));
//    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("src/imagenes/fondo.jpg"));
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

        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        constrain.anchor = GridBagConstraints.CENTER;

        crearVistaIntroduccionDatos();
        setVisible(true);
    }


    public void crearVistaIntroduccionDatos() {
        crearElementos();
        anadirElementos();
        estiloElementos();
    }


    public void crearElementos() {
        panel1 = new JPanel();
        tema1 = new JPanel();
        tema2 = new JPanel();
        tema3 = new JPanel();

        labelNombre = new JLabel("Nombre: ");
        nombre = new TextField("Nombre");

        labelTema = new JLabel("Tema");
        labelDificultad = new JLabel("Dificultad");
        CheckboxGroup cbg = new CheckboxGroup();

        facil = new Checkbox("Facil", cbg, true);
        medio = new Checkbox("Medio", cbg, false);
        dificil = new Checkbox("Dificil", cbg, false);

        adelante = new JButton();
    }


    /**
     * Añade los elementos a la vista
     */
    public void anadirElementos() {

        constrain.gridx = 0;
        constrain.gridy = 0;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;

        add(labelNombre, constrain);

        constrain.gridx = 2;
        constrain.gridy = 0;
        add(campoNombre, constrain);

        constrain.gridx = 0;
        constrain.gridy = 2;
        add(botonFlechaAtrás, constrain);

        add(labelNombre);
        add(nombre);

        add(labelTema);
        add(tema1);
        add(tema2);
        add(tema3);

        add(labelDificultad);
        add(facil);
        add(medio);
        add(dificil);

        constrain.gridx = 2;
        constrain.gridy = 2;
        add(adelante, constrain);
    }


    /**
     * Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {
        panel1.setBackground(Color.gray);

        tema1.setBackground(Color.red);
        tema2.setBackground(Color.darkGray);
        tema3.setBackground(Color.green);

        atras.setIcon(metodo.cambiarTamano(imagenFlecha, atras.getWidth(), atras.getHeight()));
        //adelante.setIcon(metodo.cambiarTamano(fondo, adelante.getWidth(), adelante.getHeight()));
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
