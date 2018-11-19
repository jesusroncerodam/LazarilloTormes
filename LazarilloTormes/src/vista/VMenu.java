/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrMenu;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VMenu extends JMenuBar  {

    private ContrMenu controlador;
    private JMenu 
            archivo,
            partida,
            ajustes,
            irA,
            mover;
            //cargarPartida;
    private JMenuItem atras,salir;//temporalk aqui , a lo mejor en el futuro se pueden eleminar de aqui

    public VMenu(Logica logica) {
        controlador = new ContrMenu(logica, this);
        generar();
        generarPartida();
    }

    public void generar(){
        generarMenuArchivo();
        /*MenuItem A1=new MenuItem("Salir",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('1'), false)));
        Menu menuV1=new Menu("Ajustes");
        menuV1.add(A1);
        
        
            MenuItem B1=new MenuItem("Sonido",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('1'), false)));
            MenuItem B2=new MenuItem("Atras",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('2'), true)));
            MenuItem C1=new MenuItem("Sonido",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('2'), false)));
            A1.addActionListener(controlador);
            A1.setActionCommand("Salir");
            //A2.addActionListener(controlador);
            //A2.setActionCommand("atras1");
            B1.addActionListener(controlador);
            B1.setActionCommand("alante2");
            B2.addActionListener(controlador);
            B2.setActionCommand("atras2");


           // menuV1.add(A2);

            Menu menuV2=new Menu("Vehiculo 2");
            menuV2.add(B1);
            menuV2.add(B2);

            MenuBar menuBar=new MenuBar();
            menuBar.add(menuV2);

            
        this.add(menuV1);*/
        
        //ventana.setMenuBar(menuBar);
    }
    
    private void generarMenuArchivo(){
        archivo=new JMenu("Archive");
        
        atras = new JMenuItem("Go back",new ImageIcon("src/img/back.png"));
        atras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        //no va lo de abaajo
            //atras.getAccessibleContext().setAccessibleDescription("dfuhfhijdksgbjk");
        archivo.add(atras);
                
        archivo.addSeparator();//(separador);
        archivo.addSeparator();

        salir=new JMenuItem("Exit",new ImageIcon("src/img/equis.png"));
        
        archivo.add(salir);
        
        this.add(archivo);
    }
    private void generarPartida(){
        partida=new JMenu("Game");
        
        JMenu submenu=new JMenu("New Game");
            JMenuItem partidaRapida=new JMenuItem("Quick game");
            submenu.add(partidaRapida);
            
            JMenuItem partidaPersonalizada=new JMenuItem("Custom game");
            submenu.add(partidaPersonalizada);
        partida.add(submenu);
        
        JMenuItem pausaPlay=new JMenuItem("Pause/Play",cambiarTamano(new ImageIcon("src/img/playPause.png"),20,20));//poner imahgen en pequeño 20px
        partida.add(pausaPlay);
        
        partida.addSeparator();
        partida.addSeparator();
        
        JMenuItem guardarPartida=new JMenuItem("Save game");
        partida.add(guardarPartida);
        
        //cargarPartida=new JMenu("Load game");
        JMenuItem cargarPartida=new JMenuItem("Load game");
        partida.add(cargarPartida);
        
        this.add(partida);
    }
    private void ajustes(){
        ajustes=new JMenu("Game");

    }
    
    /**
     Modifica el tamaño de las imagenes

     @param icono
     @param anchoImagen
     @param altoImagen
     @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }
}
