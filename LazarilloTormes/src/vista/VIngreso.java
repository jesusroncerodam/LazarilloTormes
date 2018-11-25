package vista;


import controladores.ContrIngreso;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import trabajodi.Logica;
import trabajodi.Vista;


/*
 * @author Guillermo Manso
 * @author Jesus Roncero
 *
 */
public class VIngreso extends JPanel {

    private Vista vistaMain;
    private ContrIngreso controlador;
    private JLabel labelNombre, labelAvatar, labelTema, labelDificultad;
    private TextField campoNombre;
    private JCheckBox avatar1, avatar2, avatar3,
            tema1, tema2, tema3,
            dificultad1, dificultad2, dificultad3;
    private JButton botonFlechaAtras, botonFlechaSiguiente;
    private GridBagConstraints constrain;
//    private GridBagLayout gridLayout;
    private final int ALTOIMAGENES = 150, ANCHOIMAGENES = 120, TAMANOFUENTE = 24;

    private ImageIcon iconoAvatar1 = new ImageIcon(this.getClass().getResource("/img/avatar1.jpg")),
            iconoAvatar2 = new ImageIcon(this.getClass().getResource("/img/avatar2.jpg")),
            iconoAvatar3 = new ImageIcon(this.getClass().getResource("/img/avatar3.jpg")),
            iconoTema1 = new ImageIcon(this.getClass().getResource("/img/tema1.jpg")),
            iconoTema2 = new ImageIcon(this.getClass().getResource("/img/tema2.jpg")),
            iconoTema3 = new ImageIcon(this.getClass().getResource("/img/tema3.jpg")),
            iconoDificultad1 = new ImageIcon(this.getClass().getResource("/img/dificultad1.png")),
            iconoDificultad2 = new ImageIcon(this.getClass().getResource("/img/dificultad2.png")),
            iconoDificultad3 = new ImageIcon(this.getClass().getResource("/img/dificultad3.png")),
            imagenFlecha = new ImageIcon(this.getClass().getResource("/img/botonAtrasVistaIngreso.png")),
            imagenAvanzar = new ImageIcon(this.getClass().getResource("/img/flechaRect.png")),
            fondoRegistro = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.jpg"));

    private final Border bordeAvatar = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.ORANGE, 5), BorderFactory.createRaisedBevelBorder());
    private final Border bordeTema = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED, 5), BorderFactory.createRaisedBevelBorder());
    private final Border bordeDificultad = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.YELLOW, 3), BorderFactory.createRaisedBevelBorder());

    /**
     * 
     * @param logica
     * @param vistaMain 
     */
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
    private Font bakerville(int tamanofuente) {
        Font fuente = new Font("Baskerville Old Face", Font.BOLD, tamanofuente);
        return fuente;
    }


    /**
     * Es el encargado de generar todos los elementos de la vista
     */
    public void generar() {
        anadirDescripciones();
        controlador.mandarControlador();
        this.setOpaque(false);

//        setPreferredSize(new Dimension(2, 2));
        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        constrainPorDefecto();
        crearLabelNombre();
        crearCampoNombre();
        crearAvatar();
        crearTema();
        crearDificultad();
        crearBotones();

        anadirescuchadores();
        setVisible(true);
    }


    /**
     * Crea el label del nombre
     */
    private void crearLabelNombre() {
        labelNombre = new JLabel("Nickname: ");
        labelNombre.setFont(bakerville(TAMANOFUENTE));
        labelNombre.setForeground(Color.red);
        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridy = 0;
        constrain.gridx = 0;
        add(labelNombre, constrain);
        constrainPorDefecto();
    }


    /**
     * Crea todo lo relacionado con el campo de texto del nombre
     */
    private void crearCampoNombre() {
        campoNombre = new TextField("Nickname");
        campoNombre.setFont(bakerville(TAMANOFUENTE - 8));
//        constrain.anchor = GridBagConstraints.LINE_START;
        constrain.gridwidth = 2;
        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridy = 0;
        constrain.gridx = 1;
        add(campoNombre, constrain);
        campoNombre.setBackground(null);
        constrainPorDefecto();
    }


    /**
     * Crea todo lo relacionado con los iconos de los avatares y el jlabel
     * Añade los checkbox a un grupo para elegir solo uno
     * Se les quita la opacidad
     * Se les permite tener borde
     * Se les asigna el borde correspondiente
     * Se les asigna la imagen
     */
    private void crearAvatar() {
        labelAvatar = new JLabel("Avatar: ");
        labelAvatar.setFont(bakerville(TAMANOFUENTE));
        labelAvatar.setForeground(Color.red);
        ButtonGroup grupoAvatar = new ButtonGroup();
        avatar1 = new JCheckBox("", true);
        avatar2 = new JCheckBox("", false);
        avatar3 = new JCheckBox("", false);
        grupoAvatar.add(avatar1);
        grupoAvatar.add(avatar2);
        grupoAvatar.add(avatar3);

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
        avatar1.setIcon(cambiarTamano(iconoAvatar1, ANCHOIMAGENES, ALTOIMAGENES));
        avatar2.setIcon(cambiarTamano(iconoAvatar2, ANCHOIMAGENES, ALTOIMAGENES));
        avatar3.setIcon(cambiarTamano(iconoAvatar3, ANCHOIMAGENES, ALTOIMAGENES));
        avatar1.setBorderPainted(true);
        avatar2.setBorderPainted(true);
        avatar3.setBorderPainted(true);
        avatar1.setBorder(bordeAvatar);
        avatar2.setBorder(null);
        avatar3.setBorder(null);
    }


    /**
     * Crea todo lo relacionado con los temas
     * Añade los checkbox a un grupo para elegir solo uno
     * Se les quita la opacidad
     * Se les permite tener borde
     * Se les asigna el borde correspondiente
     * Se les asigna la imagen
     */
    private void crearTema() {
        labelTema = new JLabel("Theme");
        labelTema.setFont(bakerville(TAMANOFUENTE));
        labelTema.setForeground(Color.red);
        ButtonGroup grupoTema = new ButtonGroup();
        tema1 = new JCheckBox("", true);
        tema2 = new JCheckBox("", false);
        tema3 = new JCheckBox("", false);
        grupoTema.add(tema1);
        grupoTema.add(tema2);
        grupoTema.add(tema3);

        constrain.gridy = 2;
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
        tema1.setIcon(cambiarTamano(iconoTema1, ANCHOIMAGENES, ALTOIMAGENES));
        tema2.setIcon(cambiarTamano(iconoTema2, ANCHOIMAGENES, ALTOIMAGENES));
        tema3.setIcon(cambiarTamano(iconoTema3, ANCHOIMAGENES, ALTOIMAGENES));
        tema1.setBorderPainted(true);
        tema2.setBorderPainted(true);
        tema3.setBorderPainted(true);
        tema1.setBorder(bordeTema);
        tema2.setBorder(null);
        tema3.setBorder(null);
    }


    /**
     * Crea todo lo relacionado con las dificultades
     * Añade los checkbox a un grupo para elegir solo uno
     * Se les quita la opacidad
     * Se les permite tener borde
     * Se les asigna el borde correspondiente
     * Se les asigna la imagen
     */
    private void crearDificultad() {
        labelDificultad = new JLabel("Difficulty");
        labelDificultad.setFont(bakerville(TAMANOFUENTE));
        labelDificultad.setForeground(Color.red);
        ButtonGroup grupoDificultad = new ButtonGroup();
        dificultad1 = new JCheckBox("", true);
        dificultad2 = new JCheckBox("", false);
        dificultad3 = new JCheckBox("", false);
        grupoDificultad.add(dificultad1);
        grupoDificultad.add(dificultad2);
        grupoDificultad.add(dificultad3);

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
        dificultad1.setIcon(cambiarTamano(iconoDificultad1, ANCHOIMAGENES + 40, ALTOIMAGENES - 60));
        dificultad2.setIcon(cambiarTamano(iconoDificultad2, ANCHOIMAGENES + 40, ALTOIMAGENES - 60));
        dificultad3.setIcon(cambiarTamano(iconoDificultad3, ANCHOIMAGENES + 40, ALTOIMAGENES - 60));
        dificultad1.setBorderPainted(true);
        dificultad2.setBorderPainted(true);
        dificultad3.setBorderPainted(true);
        dificultad1.setBorder(bordeDificultad);
        dificultad2.setBorder(null);
        dificultad3.setBorder(null);
    }


    /**
     * Crea todo lo relacionado con los botones
     */
    private void crearBotones() {
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
        botonFlechaSiguiente.setOpaque(false);
        botonFlechaAtras.setContentAreaFilled(false);
        botonFlechaSiguiente.setContentAreaFilled(false);
        botonFlechaAtras.setBorder(null);
        botonFlechaSiguiente.setBorder(null);
    }


    /**
     * Añade las descripciones para guardar las imágenes y que se muestren en la
     * pantalla de las estadsticas
     */
    private void anadirDescripciones() {
        iconoAvatar1.setDescription("/img/avatar1.jpg");
        iconoAvatar2.setDescription("/img/avatar2.jpg");
        iconoAvatar3.setDescription("/img/avatar3.jpg");
//        iconoTema1.setDescription("/img/tema1.jpg");
//        iconoTema2.setDescription("/img/tema2.jpg");
//        iconoTema3.setDescription("/img/tema3.jpg");
//        iconoDificultad1.setDescription("/img/dificultad1.jpg");
//        iconoDificultad2.setDescription("/img/dificultad2.jpg");
//        iconoDificultad3.setDescription("/img/dificultad3.jpg");
    }


    /**
     * Añade todos los escuchadores para relacionarlos con el controlador
     */
    private void anadirescuchadores() {
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
     * Asigna todas las constrain al valor inicial
     */
    private void constrainPorDefecto() {
        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weighty = 1.0; //para que se estiren las columnas
        constrain.weightx = 1.0; // El área de texto ocupa 1 filasa
        constrain.fill = GridBagConstraints.BOTH;
        constrain.gridwidth = 1;
        constrain.insets = new Insets(0, 0, 0, 0);
    }


    /**
     * Asigna el borde a los avatares para después comprobar que avatar ha
     * elegido el usuario
     * @param avatar int que devuelve el avatar elegido
     */
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


    /**
     * Asigna el borde a los temas para después comprobar que tema ha
     * elegido el usuario
     * @param tema int que devuelve el tema elegido
     */
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


    /**
     * Asigna el borde a la dificultad para despues comrpobar que dificultad ha
     * elegido el usuario
     * @param dificultad int que devuelve la dificultad elegida
     */
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


    /**
     * Modifica el tamaño de los iconos
     * @param icono       objeto tipo ImageIcon que se pasa para cambiarle el
     *                    tamaño
     * @param anchoImagen int que indica el nuevo ancho
     * @param altoImagen  int que indica el nuevo alto
     * @return ImageIcon que se igual al original para actualizar el tamaño
     */
    private ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        return new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH));
    }


    /*
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     * GETTERS Y SETTERS
     */
    /**
     * Retorna el texto del campo nombre para ver que nickname ha elegido el
     * usuario
     * @return string, nombre escrito en el textfield
     */
    public String recogerNombre() {
        return campoNombre.getText();
    }


    /**
     * Le manda el avatar a las estadísticas para mostrarlo en el listado
     *
     * Analiza que avatar tiene el borde y comprueba que el que no sea nulo, sea
     * elegido
     * @return String, la descripcion del avatar con la ruta del mismo
     */
    private String recogerAvatar() {
        if (avatar1.getBorder() != null) {
            System.out.println("------>" + ((ImageIcon) avatar1.getIcon()).getDescription());
            return iconoAvatar1.getDescription();//((ImageIcon) iconoAvatar1.getIcon()).getDescription();
        }
        if (avatar2.getBorder() != null) {
            return iconoAvatar2.getDescription();//((ImageIcon) avatar2.getIcon()).getDescription();
        }
        if (avatar3.getBorder() != null) {
            return iconoAvatar3.getDescription();//((ImageIcon) avatar3.getIcon()).getDescription();
        }
        return iconoAvatar1.getDescription();//((ImageIcon) avatar1.getIcon()).getDescription();
    }


    /**
     * Manda el tema para que lo recoja la vista de estadisticas
     * @return int que corresponde al tema elegido
     */
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


    /**
     * Retorna un int que indica que dificultad ha elegido el usuario, si no ha
     * elegido ninguna, se asigna la 1 de manera predeterminada
     * @return int, corresponde a la dificultad elegida
     */
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


    /**
     * Le manda todos los datos recogidos a estadisticas
     */
    public void mandarDatos() {
        controlador.mandarDatos(recogerAvatar(), recogerTema(), recogerDificultad(), recogerNombre());
    }


    /**
     * Este metodo se utiliza para cambiar el Avatar 1 por una imagen elegida
     * del sistema
     *
     * Es llamado desde el controladorIngreso por el método
     * establecerImagenElegida
     * @param imagenElegida
     */
    public void establecerImagenElegida(String imagenElegida) {
        ImageIcon icono = new ImageIcon(imagenElegida);
        avatar1.setIcon(cambiarTamano(icono, ANCHOIMAGENES, ALTOIMAGENES));
        iconoAvatar1.setDescription(imagenElegida);
    }


    /**
     * Es el metodo que se encarga de vincular la vista principal con el resto,
     * es comun en todas las vistas
     *
     * le manda un string que viene de la logica e indica a que vista nos
     * estamos moviendo
     * @param vista String, es el nombre de la vista a la que vamos a cambiar
     */
    public void cambiarDeVista(String vista) {
        vistaMain.cambiarVista(vista);
    }
}
