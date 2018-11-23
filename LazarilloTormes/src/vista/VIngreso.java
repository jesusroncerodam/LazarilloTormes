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
import javax.swing.border.Border;
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

    private final int ALTOIMAGENES = 150, ANCHOIMAGENES = 120;

    private ImageIcon iconoAvatar1 = new ImageIcon(this.getClass().getResource("/img/avatar1.jpg"));
    private ImageIcon iconoAvatar2 = new ImageIcon(this.getClass().getResource("/img/avatar2.jpg"));
    private ImageIcon iconoAvatar3 = new ImageIcon(this.getClass().getResource("/img/avatar3.jpg"));

    private ImageIcon iconoTema1 = new ImageIcon(this.getClass().getResource("/img/tema1.jpg"));
    private ImageIcon iconoTema2 = new ImageIcon(this.getClass().getResource("/img/tema2.jpg"));
    private ImageIcon iconoTema3 = new ImageIcon(this.getClass().getResource("/img/tema3.jpg"));

    private ImageIcon iconoDificultad1 = new ImageIcon(this.getClass().getResource("/img/dificultad1.png"));
    private ImageIcon iconoDificultad2 = new ImageIcon(this.getClass().getResource("/img/dificultad2.png"));
    private ImageIcon iconoDificultad3 = new ImageIcon(this.getClass().getResource("/img/dificultad3.png"));

    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/atras.png"));
    private ImageIcon imagenAvanzar = new ImageIcon(this.getClass().getResource("/img/flechaRect.png"));
    private ImageIcon fondoRegistro = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.gif"));

    private final Border bordeAvatar = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE, 5), BorderFactory.createRaisedBevelBorder());
    private final Border bordeTema = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 5), BorderFactory.createRaisedBevelBorder());
    private final Border bordeDificultad = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.YELLOW, 3), BorderFactory.createRaisedBevelBorder());


    public VIngreso(Logica logica, Vista vistaMain) {
        this.vistaMain = vistaMain;
        controlador = new ContrIngreso(this, logica);
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


    public void generar() {
        controlador.mandarControlador();
        this.setOpaque(false);

//        setPreferredSize(new Dimension(2, 2));
        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constrainPorDefecto();

        crearElementos();
        anadirescuchadores();
        setVisible(true);
    }


    public void crearElementos() {
        constrainPorDefecto();

        crearLabelNombre();
        crearCampoNombre();
        crearAvatar();
        crearTema();
        crearDificultad();

        botonFlechaAtras = new JButton(cambiarTamano(imagenFlecha, 100, 100));
        botonFlechaSiguiente = new JButton(cambiarTamano(imagenAvanzar, 100, 90));

        /*
         * BOTONES
         */
        constrain.gridy = 4;

        constrain.gridx = 0;
        add(botonFlechaAtras, constrain);
        constrain.gridx = 3;
        add(botonFlechaSiguiente, constrain);

        botonFlechaAtras.setOpaque(false);
        botonFlechaAtras.setContentAreaFilled(false);
        botonFlechaAtras.setBorder(null);

        botonFlechaSiguiente.setOpaque(false);
        botonFlechaSiguiente.setContentAreaFilled(false);
        botonFlechaSiguiente.setBorder(null);
    }

    private int TAMANOFUENTE = 24;


    public void crearLabelNombre() {
        labelNombre = new JLabel("Nickname: ");
        labelNombre.setFont(bakerville(TAMANOFUENTE));

        constrain.fill = GridBagConstraints.BASELINE;

        constrain.gridy = 0;
        constrain.gridx = 0;
        add(labelNombre, constrain);

        constrainPorDefecto();
    }


    public void crearCampoNombre() {
        campoNombre = new TextField("Nickname");
        campoNombre.setFont(bakerville(TAMANOFUENTE - 8));
//        constrain.weighty = 0.0;
//        constrain.weightx = 0.0;
        constrain.gridwidth = 2;
//        .NONE hace que el campo de texto no aparezca ocupando gran parte del cuadrante
        constrain.fill = GridBagConstraints.BASELINE;
//        constrain.anchor = GridBagConstraints.LINE_START;

        constrain.gridy = 0;
        constrain.gridx = 1;
        add(campoNombre, constrain);

        campoNombre.setBackground(null);

        constrainPorDefecto();
    }


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
        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridy = 1;

        constrain.gridx = 0;
        add(labelAvatar, constrain);

        constrain.gridx = 1;
        add(avatar1, constrain);
        constrain.gridx = 2;
        add(avatar2, constrain);
        constrain.gridx = 3;
        add(avatar3, constrain);

        avatar1.setOpaque(false);
        avatar2.setOpaque(false);
        avatar3.setOpaque(false);
        avatar1.setBorderPainted(true);
        avatar2.setBorderPainted(true);
        avatar3.setBorderPainted(true);

        iconoAvatar1 = cambiarTamano(iconoAvatar1, ANCHOIMAGENES, ALTOIMAGENES);
        iconoAvatar2 = cambiarTamano(iconoAvatar2, ANCHOIMAGENES, ALTOIMAGENES);
        iconoAvatar3 = cambiarTamano(iconoAvatar3, ANCHOIMAGENES, ALTOIMAGENES);

        avatar1.setIcon(iconoAvatar1);
        avatar2.setIcon(iconoAvatar2);
        avatar3.setIcon(iconoAvatar3);
        avatar1.setBorder(bordeAvatar);
        avatar2.setBorder(null);
        avatar3.setBorder(null);
        anadirDescripciones();
    }


    public void crearTema() {
        labelTema = new JLabel("Theme");
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

        tema1.setOpaque(false);
        tema2.setOpaque(false);
        tema3.setOpaque(false);
        tema1.setBorderPainted(true);
        tema2.setBorderPainted(true);
        tema3.setBorderPainted(true);

        tema1.setIcon(cambiarTamano(iconoTema1, ANCHOIMAGENES, ALTOIMAGENES));
        tema2.setIcon(cambiarTamano(iconoTema2, ANCHOIMAGENES, ALTOIMAGENES));
        tema3.setIcon(cambiarTamano(iconoTema3, ANCHOIMAGENES, ALTOIMAGENES));
        tema1.setBorder(bordeTema);
        tema2.setBorder(null);
        tema3.setBorder(null);
    }


    public void crearDificultad() {
        labelDificultad = new JLabel("Difficulty");
        labelDificultad.setFont(bakerville(TAMANOFUENTE));
        ButtonGroup grupoDificultad = new ButtonGroup();
        dificultad1 = new JCheckBox("", true);
        dificultad2 = new JCheckBox("", false);
        dificultad3 = new JCheckBox("", false);
        grupoDificultad.add(dificultad1);
        grupoDificultad.add(dificultad2);
        grupoDificultad.add(dificultad3);

        /*
         * COLOCAR DIFICULTAD
         */
//        constrain.insets = new Insets(0, 50, 0, 0);
        constrain.gridy = 3; //Le asigno la Y una unica vez
        constrain.gridx = 0;
        add(labelDificultad, constrain);

//        constrainPorDefecto();
        constrain.fill = GridBagConstraints.BASELINE;

        constrain.gridx = 1;
        add(dificultad1, constrain);
        constrain.gridx = 2;
        add(dificultad2, constrain);
        constrain.gridx = 3;
        add(dificultad3, constrain);

        dificultad1.setOpaque(false);
        dificultad2.setOpaque(false);
        dificultad3.setOpaque(false);
        dificultad1.setBorderPainted(true);
        dificultad2.setBorderPainted(true);
        dificultad3.setBorderPainted(true);

        dificultad1.setIcon(cambiarTamano(iconoDificultad1, ANCHOIMAGENES + 20, ALTOIMAGENES - 50));
        dificultad2.setIcon(cambiarTamano(iconoDificultad2, ANCHOIMAGENES + 20, ALTOIMAGENES - 50));
        dificultad3.setIcon(cambiarTamano(iconoDificultad3, ANCHOIMAGENES + 20, ALTOIMAGENES - 50));
        dificultad1.setBorder(bordeDificultad);
        dificultad2.setBorder(null);
        dificultad3.setBorder(null);
    }


    public void anadirDescripciones() {
        iconoAvatar1.setDescription("/img/avatar1.jpg");
        iconoAvatar2.setDescription("/img/avatar2.jpg");
        iconoAvatar3.setDescription("/img/avatar3.jpg");

//        iconoTema1.setDescription("/img/tema1.jpg");
//        iconoTema2.setDescription("/img/tema2.jpg");
//        iconoTema3.setDescription("/img/tema3.jpg");
//
//        iconoDificultad1.setDescription("/img/dificultad1.jpg");
//        iconoDificultad2.setDescription("/img/dificultad2.jpg");
//        iconoDificultad3.setDescription("/img/dificultad3.jpg");
    }


    public void anadirescuchadores() {
        labelAvatar.setName("avatar");
        campoNombre.setName("campoNombre");

        avatar1.setName("avatar1");
        avatar2.setName("avatar2");
        avatar3.setName("avatar3");

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

        avatar1.addItemListener(controlador);
        avatar2.addItemListener(controlador);
        avatar3.addItemListener(controlador);

        tema1.addItemListener(controlador);
        tema2.addItemListener(controlador);
        tema3.addItemListener(controlador);

        dificultad1.addItemListener(controlador);
        dificultad2.addItemListener(controlador);
        dificultad3.addItemListener(controlador);

        botonFlechaAtras.addMouseListener(controlador);
        botonFlechaSiguiente.addMouseListener(controlador);
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


    public void asignarBordeAvatar(int avatar) {
        avatar1.setBorder(null);
        avatar2.setBorder(null);
        avatar3.setBorder(null);
        switch (avatar) {
            case 1:
                avatar1.setBorder(bordeAvatar);
                break;
            case 2:
                avatar2.setBorder(bordeAvatar);
                break;
            case 3:
                avatar3.setBorder(bordeAvatar);
                break;
            default:
        }
        updateUI();
    }


    public void asignarBordeTema(int tema) {
        tema1.setBorder(null);
        tema2.setBorder(null);
        tema3.setBorder(null);
        switch (tema) {
            case 1:
                tema1.setBorder(bordeTema);
                break;
            case 2:
                tema2.setBorder(bordeTema);
                break;
            case 3:
                tema3.setBorder(bordeTema);
                break;
            default:
        }
        updateUI();
    }


    public void asignarBordeDificultad(int dificultad) {
        dificultad1.setBorder(null);
        dificultad2.setBorder(null);
        dificultad3.setBorder(null);
        switch (dificultad) {
            case 1:
                dificultad1.setBorder(bordeDificultad);
                break;
            case 2:
                dificultad2.setBorder(bordeDificultad);
                break;
            case 3:
                dificultad3.setBorder(bordeDificultad);
                break;
            default:
        }
        updateUI();
    }


    @Override
    public void paint(Graphics g) {
        updateUI();
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


    /*
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     */
    public String getTextoCampoNombre() {
        return campoNombre.getText();
    }


    private String recogerAvatar() {
        if (avatar1.getBorder() != null) {
            return ((ImageIcon) avatar1.getIcon()).getDescription();
        }
        if (avatar2.getBorder() != null) {
            return ((ImageIcon) avatar2.getIcon()).getDescription();
        }
        if (avatar3.getBorder() != null) {
            return ((ImageIcon) avatar3.getIcon()).getDescription();
        }
        return ((ImageIcon) avatar1.getIcon()).getDescription();
    }


    private int recogerTema() {
        if (tema1.getBorder() != null) {
            return 1;
        }
        if (tema2.getBorder() != null) {
            return 2;
        }
        if (tema3.getBorder() != null) {
            return 3;
        }
        return 1;
    }


    private int recogerDificultad() {
        if (dificultad1.getBorder() != null) {
            return 1;
        }
        if (dificultad2.getBorder() != null) {
            return 2;
        }
        if (dificultad3.getBorder() != null) {
            return 3;
        }
        return 1;
    }


    public String recogerNombre() {
        return campoNombre.getText();
    }


    public void mandarDatos() {
        controlador.mandarDatos(recogerAvatar(), recogerTema(), recogerDificultad(), recogerNombre());
    }


    /*
     *
     * private TextField campoNombre;
     *
     * private JCheckBox avatar1, avatar2, avatar3;
     * private JCheckBox tema1, tema2, tema3;
     * private JCheckBox dificultad1, dificultad2, dificultad3;
     */
    public void establecerImagenElegida(String imagenElegida) {
        ImageIcon icono = new ImageIcon(imagenElegida);
        avatar1.setIcon(cambiarTamano(icono, ANCHOIMAGENES, ALTOIMAGENES));
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
