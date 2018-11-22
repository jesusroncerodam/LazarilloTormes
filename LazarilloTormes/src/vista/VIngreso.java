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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
    private JLabel labelNombre, labelAvatar, labelTema, labelDificultad;

    //CON CONTROLADOR
    private TextField campoNombre;

    private JCheckBox avatar1, avatar2, avatar3;
    private JCheckBox tema1, tema2, tema3;
    private JCheckBox dificultad1, dificultad2, dificultad3;

    private JButton botonFlechaAtras, botonFlechaSiguiente;

    private GridBagConstraints constrain;
//    private GridBagLayout gridLayout;

    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));
    private ImageIcon fondoRegistro = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.jpg"));


    public VIngreso(Logica logica, Vista vistaMain) {
        this.vistaMain = vistaMain;
        controlador = new ContrIngreso(this, logica);
    }


    public void anadirescuchadores() {
        labelAvatar.setName("avatar");

        tema1.setName("tema1");
        tema2.setName("tema2");
        tema3.setName("tema3");

        labelDificultad.setName("labelDificultad");

        dificultad1.setName("dificultad1");
        dificultad2.setName("dificultad2");
        dificultad3.setName("dificultad3");

        botonFlechaAtras.setName("botonFlechaAtras");
        botonFlechaSiguiente.setName("botonFlechaSiguiente");

//        Escuchadores
        labelAvatar.addMouseListener(controlador);
        campoNombre.addTextListener(controlador);

        tema1.addItemListener(controlador);
        tema2.addItemListener(controlador);
        tema3.addItemListener(controlador);

        dificultad1.addItemListener(controlador);
        dificultad2.addItemListener(controlador);
        dificultad3.addItemListener(controlador);

        botonFlechaAtras.addMouseListener(controlador);
        botonFlechaSiguiente.addMouseListener(controlador);
    }


    /**
     * Crea una fuente para aplicarla a los label
     * @param tamanofuente int que hay que pasar para asignar el tamaño a la
     *                     fuente
     * @return font Devuelve una fuente
     */
    public Font bakerville(int tamanofuente) {
        Font fuente = new Font("Baskerville Old Face", Font.PLAIN, tamanofuente);
        return fuente;
    }


    public void generar() {
        this.setOpaque(false);

//        setPreferredSize(new Dimension(2, 2));
        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constrainPorDefecto();

        crearElementos();
        estiloElementos();
        anadirescuchadores();
        setVisible(true);
    }


    public void crearElementos() {
        crearLabelNombre();

        crearCampoNombre();

        crearAvatar();

        crearTema();

        crearDificultad();

        imagenFlecha = metodo.cambiarTamano(imagenFlecha, 50, 50);
        botonFlechaAtras = new JButton(imagenFlecha);
        botonFlechaSiguiente = new JButton(imagenFlecha);

        /*
         * BOTONES
         */
        constrain.gridy = 4;

        constrain.gridx = 0;
        add(botonFlechaAtras, constrain);
        constrain.gridx = 3;
        add(botonFlechaSiguiente, constrain);
    }

    private int TAMANOFUENTE = 22;


    public void crearLabelNombre() {
        labelNombre = new JLabel("Nombre: ");
        labelNombre.setFont(bakerville(TAMANOFUENTE));

//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;
        constrain.gridy = 0;
        constrain.gridx = 0;
        add(labelNombre, constrain);

//        lReloj.setHorizontalTextPosition(SwingConstants.CENTER);
//        lReloj.setVerticalTextPosition(SwingConstants.CENTER);
    }


    public void crearCampoNombre() {
        campoNombre = new TextField("Introduce tu nombre");
        campoNombre.setFont(bakerville(TAMANOFUENTE - 2));
//        constrain.weighty = 0.0;
//        constrain.weightx = 0.0;
        constrain.gridwidth = 2;
//        .NONE hace que el campo de texto no aparezca ocupando gran parte del cuadrante
        constrain.fill = GridBagConstraints.NONE;
        /*
         * campoNombre.setPreferredSize(new Dimension(50, 50));
         * campoNombre.setSize(50, 50);
         */
//        constrain.anchor = GridBagConstraints.LINE_START;
        constrain.gridy = 0;
        constrain.gridx = 1;
//        add(campoNombre, constrain);

        constrainPorDefecto();
    }

    private ImageIcon iconoTema1 = new ImageIcon(this.getClass().getResource("/img/avatar1.jpg"));
    private ImageIcon iconoTema2 = new ImageIcon(this.getClass().getResource("/img/avatar2.jpg"));
    private ImageIcon iconoTema3 = new ImageIcon(this.getClass().getResource("/img/avatar3.jpg"));


    public void crearAvatar() {
        labelAvatar = new JLabel("Avatar: ");
        labelAvatar.setFont(bakerville(TAMANOFUENTE));
        ButtonGroup grupoAvatar = new ButtonGroup();
        avatar1 = new JCheckBox("", true);
        avatar2 = new JCheckBox("", false);
        avatar3 = new JCheckBox("", false);
        grupoAvatar.add(avatar1);
        grupoAvatar.add(avatar2);
        grupoAvatar.add(avatar3);
        /*
         * COLOCAR AVATAR
         */
        constrainPorDefecto();

        constrain.gridy = 1;

        constrain.gridx = 0;
        add(labelAvatar, constrain);

        constrainPorDefecto();

        constrain.gridx = 1;
        add(avatar1, constrain);
        constrain.gridx = 2;
        add(avatar2, constrain);
        constrain.gridx = 3;
        add(avatar3, constrain);
    }
    private ImageIcon iconoTema1 = new ImageIcon(this.getClass().getResource("/img/tema1.jpg"));
    private ImageIcon iconoTema2 = new ImageIcon(this.getClass().getResource("/img/tema2.jpg"));
    private ImageIcon iconoTema3 = new ImageIcon(this.getClass().getResource("/img/tema3.jpg"));


    public void crearTema() {
        labelTema = new JLabel("Tema");
        labelTema.setFont(bakerville(TAMANOFUENTE));
        ButtonGroup grupoTema = new ButtonGroup();
        tema1 = new JCheckBox("", true);
        tema2 = new JCheckBox("", false);
        tema3 = new JCheckBox("", false);
        grupoTema.add(tema1);
        grupoTema.add(tema2);
        grupoTema.add(tema3);

        /*
         * COLOCAR TEMA
         */
        constrain.gridy = 2;
        //        le asigno la Y una sola vez 
        constrain.gridx = 0;
        add(labelTema, constrain);

        constrainPorDefecto();
        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridx = 1;
        add(tema1, constrain);
        constrain.gridx = 2;
        add(tema2, constrain);
        constrain.gridx = 3;
        add(tema3, constrain);

        final int ALTITO = 110, ANCHITO = 85;

        iconoTema1 = cambiarTamano(iconoTema1, ANCHITO, ALTITO);
        iconoTema2 = cambiarTamano(iconoTema2, ANCHITO, ALTITO);
        iconoTema3 = cambiarTamano(iconoTema3, ANCHITO, ALTITO);

        tema1.setIcon(iconoTema1);
        tema2.setIcon(iconoTema2);
        tema3.setIcon(iconoTema3);
        tema1.setBorder(BorderFactory.createRaisedBevelBorder());
        tema2.setBorder(null);
        tema3.setBorder(null);
    }


    public void asignarBordeTema(int tema) {

        tema1.setBorder(null);
        tema2.setBorder(null);
        tema3.setBorder(null);

        switch (tema) {
            case 1:
                tema1.setBorder(BorderFactory.createRaisedBevelBorder());
                break;

            case 2:
                tema2.setBorder(BorderFactory.createRaisedBevelBorder());
                break;

            case 3:
                tema3.setBorder(BorderFactory.createRaisedBevelBorder());
                break;

            default:

        }
    }


    public void crearDificultad() {
        labelDificultad = new JLabel("Dificultad");
        labelDificultad.setFont(bakerville(TAMANOFUENTE));
        ButtonGroup grupoDificultad = new ButtonGroup();
        dificultad1 = new JCheckBox("", true);
        dificultad2 = new JCheckBox("", false);
        dificultad3 = new JCheckBox("", false);
        grupoDificultad.add(dificultad1);
        grupoDificultad.add(dificultad2);
        grupoDificultad.add(dificultad3);

//        facil.setIcon(imagenFlecha);
//        medio.setIcon(imagenFlecha);
        /*
         * COLOCAR DIFICULTAD
         */
//        constrain.insets = new Insets(0, 50, 0, 0);
        constrain.gridy = 3; //Le asigno la Y una unica vez
        constrain.gridx = 0;
        add(labelDificultad, constrain);

        constrainPorDefecto();

        constrain.gridx = 1;
        add(dificultad1, constrain);
        constrain.gridx = 2;
        add(dificultad2, constrain);
        constrain.gridx = 3;
        add(dificultad3, constrain);
    }


    /*
     * Añade los elementos a la vista
     *
     * loc.ipadx = 50;
     * loc.anchor = GridBagConstraints.LINE_START;
     */
    public void constrainPorDefecto() {
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
     * Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {
        avatar1.setOpaque(false);
        avatar2.setOpaque(false);
        avatar3.setOpaque(false);

        tema1.setOpaque(false);
        tema2.setOpaque(false);
        tema3.setOpaque(false);
        tema1.setBorderPainted(true);
        tema2.setBorderPainted(true);
        tema3.setBorderPainted(true);
//        tema1.setBackground(colorAleatorio());
//        tema2.setBackground(colorAleatorio());
//        tema3.setBackground(colorAleatorio());
//        avatar1.setBackground(colorAleatorio());
//        avatar2.setBackground(colorAleatorio());
//        avatar3.setBackground(colorAleatorio());
//        facil.setBackground(colorAleatorio());
//        medio.setBackground(colorAleatorio());
//        dificil.setBackground(colorAleatorio());

        dificultad1.setOpaque(false);
        dificultad2.setOpaque(false);
        dificultad3.setOpaque(false);
//        botonFlechaAtras.setOpaque(false);
//        botonFlechaAtras.setContentAreaFilled(false);
        botonFlechaAtras.setBorder(null);

//        botonFlechaSiguiente.setOpaque(false);
//        botonFlechaSiguiente.setContentAreaFilled(false);
        botonFlechaSiguiente.setBorder(null);
    }


    @Override
    public void paint(Graphics g) {

        g.drawImage(fondoRegistro.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
    }


    public void cambiarDeVista(String vista) {
        vistaMain.cambiarVista(vista);
    }


    /**
     * Proporciona un color aleatorio para el panel
     * @return Devuelve un color
     */
    public Color colorAleatorio() {
        Random aleatorio = new Random();
        int red = aleatorio.nextInt(255);
        int green = aleatorio.nextInt(255);
        int blue = aleatorio.nextInt(255);
        Color color = new Color(red, green, blue);
        return color;
    }


    /**
     * Modifica el tamaño de las imagenes
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }


    /**
     * Modifica el tamaño de las imagenes
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public Image cambiarTamano(Image icono, int anchoImagen, int altoImagen) {
        Image reescalada = icono.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        return reescalada;
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
