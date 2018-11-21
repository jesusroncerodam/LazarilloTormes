/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Guille
 */
public class VPrincipal extends JPanel {

    private Vista vistaMain;
    private ControladorPrincipal controlador;
    private GridBagConstraints constrain;
    private JButton bNuevaPartida, bEstadisticas, bCargarPartida, bDialogoModal;
    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoPrinc.jpg"));
    private final Font FUENTE = new Font("Monospaced", Font.BOLD, 30);
    private final String RUTA_BOTON = "/img/botonn.png";


    public VPrincipal(Logica logica, Vista vistaMain) {
        this.vistaMain = vistaMain;
        controlador = new ControladorPrincipal(logica, this);
    }


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


    private void btnExtra() {
        JButton bExtrs = new JButton("");

        bExtrs.addMouseListener(controlador);

        bExtrs.setContentAreaFilled(false);

        constrain.gridx = 1; // El área de texto empieza en la columna 0.
        constrain.gridy = 3; // El área de texto empieza en la fila 1
        constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
        this.add(bExtrs, constrain);
    }//fondoPrinc.jpg


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
    public void cambiarDeVista(String vista) {
        vistaMain.cambiarVista(vista);
    }
}
