/*
Explicacion diagrma: los objetos (vistas)(JPnalel) se van añadiendo al 
Jframe (ventana) para despues eliminarlos o no segun lo que se quiera hacer
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

        ventana.setVisible(true);
        ingresoDatos();
       // estadisticas();
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
     * Método para generar la pantalla de carga, se le pasan las strings del logo
     * y del fondo para crear las imagenes, así como el tiempo que queremos que
     * dure la animación
     * Metodo genera la pantalla Splash,
     * @param logo  Strig,ruta de la imagen que da vueltas
     * @param fondo String, ruta de la imagen que es asignada en el fondo
     * @param tiempo Int, Tiempo en segundos, indica la duracion del splash.
     */
    private synchronized void cargarSplash(String logo, String fondo, int tiempo) {
        splash = new VistaSplash(logo, fondo, tiempo, fuente, this);
        ventana.setMinimumSize(splash.getMinimumSize());//asignamos el tamaño minimo para la ventana
        ventana.add(splash);
        ventana.setVisible(true);
        splash.empezarAnimaciones();
        try {
            wait();//dormimos la ejecucion de este hilo, termina cuando se llame a splashTermina
        } catch (InterruptedException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que finaliza la ejecución de la vista de carga, además,notificando
     * al hilo principal que estaba en "wait", duerme la ejecucion durante 0.5
     * segundos para no mostrar un cambio brusco, establece el splash a nulo para
     * ahorrar espacio en memoria y elimina la ventana de carg.
     */
    public synchronized void splashTermina() {
        notifyAll();//notificamos al hilo de ejecucion
        try {
            Thread.sleep(500);//lo dormimos un segundo para que el cambio no sea brusco
        } catch (InterruptedException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        ventana.remove(splash);//lo quitamos de la vusta para que no de errores
        splash = null;//eliminamos el objeto, ya no lo necesitamos mas 
    }


    /**
     */
    public void ingresoDatos() {
       
        String aux[] = new String[8];
        aux[0] = "src/img/carta.jpg";
        aux[1] = "src/img/2.jpg";
        aux[2] = "src/img/carga2.jpg";
        aux[3] = "src/img/flecha.png";
        aux[4] = "src/img/flecha.png";
        aux[5] = "src/img/flecha.png";
        aux[6]="src/img/flecha.png";
        aux[7]="src/img/flecha.png";

        vJuego.generar(aux);
        cargarSplash("/img/logotrini.png", "/img/carga.jpg", 0);
        ventana.setSize(1000, 1000);        
        ventana.add(vJuego);

        ventana.repaint();
    }
    
    /**
     * 
     */
    public void estadisticas() {

        vLista.generar();
        cargarSplash("/img/logotrini.png", "/img/carga.jpg", 0);
        ventana.setSize(1000, 1000);        
        ventana.add(vLista);

        ventana.repaint();
    }
}
