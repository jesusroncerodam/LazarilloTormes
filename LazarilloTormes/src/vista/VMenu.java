/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrMenu;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import trabajodi.Logica;
import trabajodi.Vista;


/**
 *
 * @author Guille
 */
public class VMenu extends JMenuBar {

    private Vista vista;
    private ContrMenu controlador;
    private JMenu archivo,
            submenu, 
            partida,
            ajustes,
            irA,
            mover;
    private JMenuItem pausaPlay, guardarPartida, cargarPartida;

    //cargarPartida;
    private JMenuItem atras, salir;//temporalk aqui , a lo mejor en el futuro se pueden eleminar de aqui


    public VMenu(Logica logica, Vista vista) {
        controlador = new ContrMenu(logica, this);
        this.vista = vista;
        generar();
        
       // modoJuego();

    }


    public void generar() {
        controlador.asignarMenuLogica();
        generarMenuArchivo();
        generarPartida();
        ajustes();
    }


    private void generarMenuArchivo() {
        archivo = new JMenu("Archive");
        
        atras = new JMenuItem("Go back", new ImageIcon("src/img/back.png"));
        atras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        atras.setActionCommand("atras");
        atras.addActionListener(controlador);
        archivo.add(atras);

        archivo.addSeparator();
        archivo.addSeparator();

        salir = new JMenuItem("Exit", new ImageIcon("src/img/equis.png"));
        salir.setActionCommand("salir");
        salir.addActionListener(controlador);
        archivo.add(salir);
        this.add(archivo);
    }


    private void generarPartida() {
        partida = new JMenu("Game");

        submenu = new JMenu("New Game");
        JMenuItem partidaRapida = new JMenuItem("Quick game");
        submenu.add(partidaRapida);

        JMenuItem partidaPersonalizada = new JMenuItem("Custom game");
        submenu.add(partidaPersonalizada);
        partida.add(submenu);

        pausaPlay = new JMenuItem("Pause/Play", cambiarTamano(new ImageIcon("src/img/playPause.png"), 20, 20));//poner imahgen en pequeño 20px
        partida.add(pausaPlay);

        partida.addSeparator();
        partida.addSeparator();

        guardarPartida = new JMenuItem("Save game");
        guardarPartida.setEnabled(false);
        guardarPartida.addActionListener(controlador);
        guardarPartida.setActionCommand("guardar");
        partida.add(guardarPartida);

        //cargarPartida=new JMenu("Load game");
        cargarPartida = new JMenuItem("Load game");
        cargarPartida.addActionListener(controlador);
        cargarPartida.setActionCommand("cargar");
        partida.add(cargarPartida);

        this.add(partida);
    }


    private void ajustes() {
        ajustes = new JMenu("Settings");
        JRadioButtonMenuItem sonido = new JRadioButtonMenuItem("Sound", true);
        ajustes.add(sonido);
        this.add(ajustes);
    }


    public void cambiarVista(String vistaCambia){
        System.out.println(vistaCambia);
        vista.cambiarVista(vistaCambia);
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
    public void modoJuego(){
        atras.setEnabled(false);
        salir.setVisible(true);
        
        submenu.setEnabled(false);
        pausaPlay.setEnabled(true);
        guardarPartida.setEnabled(true);
        cargarPartida.setEnabled(false);
        
    }
    public void normal(){
        atras.setEnabled(true);
        salir.setVisible(true);
        
        submenu.setEnabled(true);
        pausaPlay.setEnabled(false);
        guardarPartida.setEnabled(false);
        cargarPartida.setEnabled(true);
    }

}
