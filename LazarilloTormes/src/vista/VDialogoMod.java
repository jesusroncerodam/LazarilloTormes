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
import trabajodi.Vista;


/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class VDialogoMod extends JPanel {

    private Vista vistaMain;
    private ContrDialogoMod controlador;
    private JButton botonFlechaAtras;
    private JLabel labelDesarrolladores, labelNombreGuille, labelNombreJesus, labelLogoTrini;
    private GridBagConstraints constrain;
    private ImageIcon flecha = new ImageIcon(this.getClass().getResource("/img/atras.png")),
            fondo = new ImageIcon(this.getClass().getResource("/img/fondoRegistro.gif")),
            iconoTrinitarias = new ImageIcon(this.getClass().getResource("/img/logotrini.png"));

    /**
     * Constructor, solo se asigna la logica y vista, el resto se genera 
     * llamando al metodo generar
     * @param logica logica 
     * @param vista vista a la que refiere(padre)
     */
    public VDialogoMod(Logica logica, Vista vista) {
        this.vistaMain = vista;
        controlador = new ContrDialogoMod(this, logica);
    }


    /**
     * Es el encargado de generar todos los elementos de la vista
     */
    public void generar() {
        controlador.mandarControlador();
        setOpaque(false);
        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        defectoConstrain();

        crearLabelDesarrolladores();
        crearLabelNombreJesus();
        crearLabelNombreGuille();
        crearLogoTrini();
        generarBoton();

        anadirEscuchadores();
    }


    /**
     * Crea el label de los desarrolladores
     */
    public void crearLabelDesarrolladores() {
        labelDesarrolladores = new JLabel("Desarrolladores");
        labelDesarrolladores.setFont(bakerville(70));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 5;
        constrain.gridy = 0;
        constrain.gridx = 1;
        add(labelDesarrolladores, constrain);
        defectoConstrain();
    }


    /**
     * Crea el label del nombre de Guille
     */
    public void crearLabelNombreGuille() {
        labelNombreGuille = new JLabel("Guillermo Manso García");
        labelNombreGuille.setFont(bakerville(30));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 2;
        constrain.gridx = 2;
        constrain.gridy = 1;
        add(labelNombreGuille, constrain);
        defectoConstrain();
    }


    /**
     * Crea el label del nombre de Jesus
     */
    public void crearLabelNombreJesus() {
        labelNombreJesus = new JLabel("Jesús Roncero García");
        labelNombreJesus.setFont(bakerville(30));

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 2;
        constrain.gridy = 2;
        constrain.gridx = 2;
        add(labelNombreJesus, constrain);
        defectoConstrain();
    }


    /**
     * Crea el label del logo trini con la imagen dentro
     */
    private void crearLogoTrini() {
        labelLogoTrini = new JLabel(iconoTrinitarias);

        constrain.fill = GridBagConstraints.BASELINE;
        constrain.gridwidth = 4;
        constrain.gridx = 4;       //HORIZONTAL
        constrain.gridy = 4;        //VERTICAL
        add(labelLogoTrini, constrain);
        defectoConstrain();
    }


    /**
     * Genera el boton de vuelta atrás
     */
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
     * @param g graphics
     */
    @Override
    public void paint(Graphics g) {

        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);
        repaint();
    }


    /**
     * Asigna todos los escuchadores
     */
    public void anadirEscuchadores() {
        botonFlechaAtras.setActionCommand("botonAtras");
        labelLogoTrini.setName("iconoTrinitarias");

        botonFlechaAtras.addActionListener(controlador);
        labelLogoTrini.addMouseListener(controlador);
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


    /*
     * Añade los elementos a la vista
     */
    public void defectoConstrain() {
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
