/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package trabajodi;


import vista.VCarga;
import vista.VDialogoMod;
import vista.VIngreso;
import vista.VJuego;
import vista.VLista;
import vista.VMenu;
import vista.VistaSplash;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**

 @author Guille
 */
public class Vista {

    private JFrame ventana;
    private Font fuente = new Font("Agency FB", Font.BOLD, 40);

    private VCarga vCarga;
    private VDialogoMod vDialogoMod;
    private VIngreso vIngreso;
    private VJuego vJuego;
    private VLista vLista;
    private VMenu vMenu;
    private VistaSplash splash;


    /**
     Constructor principal de la vista, se ejecuta creando también la logica
     para mandarsela por parámetro al resto de las vistas

     @param logica clase logica para vincularla con todas las clases
     */
    public Vista(Logica logica) {
        generarVista();
        //creamos todas las vistas mandandole la logica
        vCarga = new VCarga(logica);
        vDialogoMod = new VDialogoMod(logica);
        vIngreso = new VIngreso(logica);
        vJuego = new VJuego(logica);
        vLista = new VLista(logica);
        vMenu = new VMenu(logica);
        //no ponemos splash ya que no necesita logica

        // vDialogoMod=new VDialogoMod(logica);
        ventana.setVisible(true);
/*<<<<<<< HEAD
        ingresoDatos() ;
=======*/
        ingresoDatos();
//>>>>>>> ad0184bed14d41c598565ef42fdd4ec2eb7c1c99
    }


    /**
     Agrupamos todas las acciones que realizamos sobre la ventana
     */
    public void generarVista() {
        ventana = new JFrame("Memorion");
        ventana.setMaximumSize(new Dimension(1924, 1047));
        //ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     Método para generar la pantalla de carga, se le pasan las strings del logo
     y del fondo para crear las imagenes, así como el tiempo que queremos que
     dure la animación
     @param logo   cadena de texto con la ruta a la imagen del logo
     @param fondo  cadena de texto con la ruta a la imagen de fondo
     @param tiempo entero que indica el tiempo que va a tardar
     */
    private synchronized void cargarSplash(String logo, String fondo, int tiempo) {
        ventana.setSize(600, 600);

        splash = new VistaSplash(logo, fondo, tiempo, fuente, this);
        ventana.setMinimumSize(splash.getMinimumSize());//asignamos el tamaño minimo para la ventana
        ventana.add(splash);
        ventana.setVisible(true);
        splash.empezarAnimaciones();
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     Método que finaliza la ejecución de la vista de carga, además, establece el
     splash a nulo para ahorrar espacio en memoria, elimina la ventana de carga,
     y vuelve a pintar la ventana para que se ejecute la nueva vista
     */
    public synchronized void splashTermina() {
        notifyAll();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventana.remove(splash);
        //ventana.repaint();
        splash = null;

        // vDialogoMod.cargar();
        // ingresoDatos();
        //ventana.repaint();

        //ventana.add(vDialogoMod);
       // ventana.setVisible(true);
    }


    /**
     // DOCUMENTACIÓN EN PROCESO
     // DOCUMENTACIÓN EN PROCESO
     // DOCUMENTACIÓN EN PROCESO
     // DOCUMENTACIÓN EN PROCESO
     // DOCUMENTACIÓN EN PROCESO
     // DOCUMENTACIÓN EN PROCESO
     */
    public void ingresoDatos() {
       
        String aux[] = new String[6];
        aux[0] = "src/img/carta.jpg";
        aux[1] = "src/img/2.jpg";
        aux[2] = "src/img/carga2.jpg";
        aux[3] = "src/img/flecha.png";
        aux[4] = "src/img/flecha.png";
        aux[5] = "src/img/flecha.png";
        //   aux[6]="src/img/flecha.png";
        vJuego.generar(aux);
        cargarSplash("/img/logotrini.png", "/img/carga.jpg", 1);
        ventana.setSize(1000, 800);        
        ventana.add(vJuego);

        ventana.repaint();
    }
}
