package creacionvistas;


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


/**
 *
 * @author JesusClase
 */
public class DialogoModal extends JFrame {

    private JFrame marco1;
    ControladorVista controladorVista;

    private JPanel panel1;
    private final int ANCHOMARCO = 500, ALTOMARCO = 500;
    private JLabel labelNombre, labelApodo;
    private TextField campoNombre;
    private JButton botonFlechaAtrás, avanzarPagina;

    private GridBagConstraints constrain;
    private GridBagLayout gridLayout = new GridBagLayout();

    private ImageIcon imagenFlecha = new ImageIcon("src/img/flecha.png");
    private ImageIcon fondo = new ImageIcon("src/imagenes/fondo.jpg");


    public DialogoModal() {
        this.marco1 = this;
        crearVista();

        labelNombre.setBounds(50, 50, 50, 50);
    }


    public void crearVista() {
        formaMarco();
        crearElementos();
        anadirElementos();
        posicionElementos();
        estiloElementos();

        anadirControladores();
        setVisible(true);
    }


    public void crearElementos() {
        panel1 = new JPanel();

        labelNombre = new JLabel();
        labelApodo = new JLabel();
        campoNombre = new TextField();

        botonFlechaAtrás = new JButton(imagenFlecha);
        avanzarPagina = new JButton();

//        constrain.gridx = 1;
//        constrain.gridy = 1;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;
        add(panel1, constrain);
        panel1.setBackground(Color.red);

        add(labelNombre);

        add(labelApodo);

        add(campoNombre);

        add(botonFlechaAtrás);

        botonFlechaAtrás = new JButton();
        add(botonFlechaAtrás);
        botonFlechaAtrás.setBounds(50, 350, 100, 50);
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


    /**
     * Añade los elementos a la vista
     */
    public void anadirElementos() {

    }


    /**
     * Asigno las posiciones a los elementos
     */
    public void posicionElementos() {

    }


    /**
     * Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {

    }


    /**
     * Le asigna la forma al marco
     */
    public void formaMarco() {
        this.setLayout(gridLayout);
        setLayout(null);
        setResizable(false);
        setBounds(550, 150, ANCHOMARCO, ALTOMARCO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Añade y crea el controlador de la vista
     */
    public void anadirControladores() {
        controladorVista = new ControladorVista(this);
    }


    /**
     * Proporciona un color aleatorio para el panel
     * @return Devuelve un color
     */
//    public Color colorAleatorio() {
//        Random aleatorio = new Random();
//        int red = aleatorio.nextInt(255);
//        int green = aleatorio.nextInt(255);
//        int blue = aleatorio.nextInt(255);
//        Color color = new Color(red, green, blue);
//        return color;
//    }
    /**
     * Modifica el tamaño de los iconos
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        return new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH));
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
        return icono.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
    }
}
