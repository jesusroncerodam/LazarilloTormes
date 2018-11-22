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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabajodi.Logica;
import trabajodi.Metodos;
import trabajodi.Vista;


/*
 * @author Guille
 *
 */
public class VIngreso extends JPanel {

    private Vista vistaMain;
    private ContrIngreso controlador;
    private Metodos metodo = new Metodos();

    //SIN CONTROLADOR
    private JLabel labelNombre, labelTema, labelDificultad;

    //CON CONTROLADOR
    private TextField campoNombre;

    private JCheckBox tema1, tema2, tema3;
    private JCheckBox facil, medio, dificil;

    private JButton botonFlechaAtrás, botonFlechaSiguiente;

    private GridBagConstraints constrain;
    private GridBagLayout gridLayout;
    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));
    private ImageIcon fondoRegistro = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.jpg"));


    public VIngreso(Logica logica, Vista vistaMain) {
        this.vistaMain = vistaMain;
        controlador = new ContrIngreso(this, logica);
    }


    public void anadirescuchadores() {

        botonFlechaAtrás.setName("botonFlechaAtras");
        botonFlechaSiguiente.setName("botonFlechaSiguiente");

        tema1.setName("tema1");
        tema2.setName("tema2");
        tema3.setName("tema3");

        facil.setName("facil");
        medio.setName("medio");
        dificil.setName("dificil");

//        Controladores
        tema1.addItemListener(controlador);
        tema2.addItemListener(controlador);
        tema3.addItemListener(controlador);

        facil.addItemListener(controlador);
        medio.addItemListener(controlador);
        dificil.addItemListener(controlador);

        campoNombre.addTextListener(controlador);
        botonFlechaAtrás.addMouseListener(controlador);
        botonFlechaSiguiente.addMouseListener(controlador);
    }


    public void generar() {
        this.setOpaque(false);

        setPreferredSize(new Dimension(2, 2));

        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weighty = 0.6; //para que se estiren las columnas
        constrain.weightx = 0.6; // El área de texto ocupa 1 filasa
        constrain.fill = GridBagConstraints.BOTH;

        crearElementos();
        anadirElementos();
        estiloElementos();

        anadirescuchadores();

        setVisible(true);
    }


    public void crearElementos() {

        labelNombre = new JLabel("Nombre: ");
        campoNombre = new TextField("Introduce tu nombre");

        labelTema = new JLabel("Tema");
        ButtonGroup grupoTema = new ButtonGroup();

        tema1 = new JCheckBox("", true);
        tema2 = new JCheckBox("", false);
        tema3 = new JCheckBox("", false);

        grupoTema.add(tema1);
        grupoTema.add(tema2);
        grupoTema.add(tema3);

        labelDificultad = new JLabel("Dificultad");
        ButtonGroup grupoDificultad = new ButtonGroup();

        facil = new JCheckBox("", true);
        medio = new JCheckBox("", false);
        dificil = new JCheckBox("", false);

        grupoDificultad.add(facil);
        grupoDificultad.add(medio);
        grupoDificultad.add(dificil);

        imagenFlecha = metodo.cambiarTamano(imagenFlecha, 50, 50);

//        facil.setIcon(imagenFlecha);
//        medio.setIcon(imagenFlecha);
        botonFlechaAtrás = new JButton(imagenFlecha);
        botonFlechaSiguiente = new JButton(imagenFlecha);
    }


    /*
     * Añade los elementos a la vista
     *
     * loc.ipadx = 50;
     * loc.anchor = GridBagConstraints.LINE_START;
     */
    public void anadirElementos() {

        constrain.insets = new Insets(25, 50, 25, 0);
        constrain.gridx = 0;
        constrain.gridy = 0;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;
        add(labelNombre, constrain);

        constrain.gridx = 1;
        constrain.gridy = 0;
        constrain.gridwidth = 2;
        constrain.fill = GridBagConstraints.NONE;
//        campoNombre.setPreferredSize(new Dimension(50, 50));
//        campoNombre.setSize(50, 50);
        constrain.insets = new Insets(25, 0, 25, 0);
        constrain.anchor = GridBagConstraints.LINE_START;

        add(campoNombre, constrain);

        /*
         * POR DEFECTO
         */
        constrain.gridwidth = 1;
        constrain.anchor = GridBagConstraints.CENTER;
        constrain.fill = GridBagConstraints.BOTH;
        constrain.insets = new Insets(0, 0, 0, 0);

        /*
         * COLOCAR TEMA
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

//        facil.setOpaque(false);
//        medio.setOpaque(false);
//        dificil.setOpaque(false);
        botonFlechaAtrás.setOpaque(false);
//        botonFlechaAtrás.setContentAreaFilled(false);
//        botonFlechaAtrás.setBorder(null);

        botonFlechaSiguiente.setOpaque(false);
//        botonFlechaSiguiente.setContentAreaFilled(false);
//        botonFlechaSiguiente.setBorder(null);
    }


    @Override
    public void paint(Graphics g) {

        g.drawImage(fondoRegistro.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
    }


    public void cambiarDeVista(String vista) {
        vistaMain.cambiarVista(vista);
    }
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
