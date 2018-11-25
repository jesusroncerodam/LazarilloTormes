package vista;


import controladores.ControladorPrincipal;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabajodi.Logica;
import trabajodi.Vista;


/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class VPrincipal extends JPanel {

    private Vista vistaMain;
    private ControladorPrincipal controlador;
    private GridBagConstraints constrain;
    private JButton bNuevaPartida, bEstadisticas, bCargarPartida, bDialogoModal;
    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoPrinc.jpg"));
    private final Font FUENTE = new Font("Monospaced", Font.BOLD, 30);
    private final String RUTA_BOTON = "/img/boton.png";

    
    public VPrincipal(Logica logica, Vista vistaMain) {
        this.vistaMain = vistaMain;
        controlador = new ControladorPrincipal(logica, this);
    }


    /**
     * Es el metodo que se encarga de llamar a todos los metodos individuales de
     * creacion de objetos
     */
    public void generar() {
        this.setOpaque(false);
        this.setFocusable(true);

        constrain = new GridBagConstraints();

        //constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        constrain.weighty = 1.0; //para que se estiren las columnas

        constrain.weightx = 1.0; // El área de texto ocupa 1 filas.
        this.setLayout(new GridBagLayout());
        btnPartida();
        btnCargarPartida();
        btnEstadisticas();
        btnDialogModal();
        btnExtra();
        repaint();
    }


    /**
     * Genera el boton de nueva partida
     */
    private void btnPartida() {
        bNuevaPartida = new JButton("New Game", new ImageIcon(this.getClass().getResource(RUTA_BOTON)));

        bNuevaPartida.addMouseListener(controlador);

        bNuevaPartida.setBorder(null);
        bNuevaPartida.setContentAreaFilled(false);
        bNuevaPartida.setFont(FUENTE);
        bNuevaPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        bNuevaPartida.setVerticalTextPosition(SwingConstants.CENTER);

        constrain.gridx = 0; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1

        this.add(bNuevaPartida, constrain);
    }


    /**
     * Genera el boton de estadisticas
     */
    private void btnEstadisticas() {
        bEstadisticas = new JButton("Stats", new ImageIcon(this.getClass().getResource(RUTA_BOTON)));

        bEstadisticas.addMouseListener(controlador);

        bEstadisticas.setBorder(null);
        bEstadisticas.setContentAreaFilled(false);
        bEstadisticas.setFont(FUENTE);
        bEstadisticas.setHorizontalTextPosition(SwingConstants.CENTER);
        bEstadisticas.setVerticalTextPosition(SwingConstants.CENTER);

        constrain.gridx = 2; // El área de texto empieza en la columna 0.
        constrain.gridy = 2; // El área de texto empieza en la fila 1
        this.add(bEstadisticas, constrain);
    }


    /**
     * Genera el boton de cargar la ultima partida guardada
     */
    private void btnCargarPartida() {
        bCargarPartida = new JButton("Load game", new ImageIcon(this.getClass().getResource(RUTA_BOTON)));

        bCargarPartida.addMouseListener(controlador);

        bCargarPartida.setBorder(null);
        bCargarPartida.setContentAreaFilled(false);
        bCargarPartida.setFont(FUENTE);
        bCargarPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        bCargarPartida.setVerticalTextPosition(SwingConstants.CENTER);

        constrain.gridx = 1; // El área de texto empieza en la columna 0.
        constrain.gridy = 2; // El área de texto empieza en la fila 1
        this.add(bCargarPartida, constrain);
    }


    /**
     * Genera el boton del dialogo modal, que contiene los datos de los
     * desarrolladores
     */
    private void btnDialogModal() {
        bDialogoModal = new JButton("About us", new ImageIcon(this.getClass().getResource(RUTA_BOTON)));

        bDialogoModal.addMouseListener(controlador);

        bDialogoModal.setBorder(null);
        bDialogoModal.setContentAreaFilled(false);
        bDialogoModal.setFont(FUENTE);
        bDialogoModal.setHorizontalTextPosition(SwingConstants.CENTER);
        bDialogoModal.setVerticalTextPosition(SwingConstants.CENTER);

        constrain.gridx = 3; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1
        this.add(bDialogoModal, constrain);
    }


    /**
     * Genera el boton que contiene el easter egg
     */
    private void btnExtra() {
        JButton bExtrs = new JButton("");

        bExtrs.addMouseListener(controlador);

        bExtrs.setContentAreaFilled(false);

        constrain.gridx = 1; // El área de texto empieza en la columna 0.
        constrain.gridy = 3; // El área de texto empieza en la fila 1
        constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
        this.add(bExtrs, constrain);
    }//fondoPrinc.jpg


    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(g);//borramos la imagen anterior
    }


    /*
     * CONTROLADOR VISTA PRINCIPAL
     * CONTROLADOR VISTA PRINCIPAL
     * CONTROLADOR VISTA PRINCIPAL
     * CONTROLADOR VISTA PRINCIPAL
     * CONTROLADOR VISTA PRINCIPAL
     */
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
