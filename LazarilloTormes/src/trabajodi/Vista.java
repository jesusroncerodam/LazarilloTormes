/*
 * Explicacion diagrma: los objetos (vistas)(JPnalel) se van añadiendo al
 * Jframe (ventana) para despues eliminarlos o no segun lo que se quiera hacer
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import vista.VPrincipal;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Guille
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
    private VPrincipal vPrincipal;
    private VistaSplash splash;


    /**
     * Constructor principal de la vista, se ejecuta creando también la logica
     * para mandarsela por parámetro al resto de las vistas
     *
     * @param logica clase logica para vincularla con todas las clases
     */
    public Vista(Logica logica) {
        generarVista();
        //creamos todas las vistas mandandole la logica
        vCarga = new VCarga(logica);
        vPrincipal = new VPrincipal(logica,this);
        vDialogoMod = new VDialogoMod(logica);
        vIngreso = new VIngreso(logica);
        vJuego = new VJuego(logica);
        vLista = new VLista(logica,this);
        vMenu = new VMenu(logica, this);
        //no ponemos splash ya que no necesita logica

        ventana.setVisible(true);
        //temporal
        ventana.setJMenuBar(vMenu);

       // ingresoDatos();
         // estadisticas();
        //principal();
        cambiarVista("principal");
    }


    /**
     * Agrupamos todas las acciones que realizamos sobre la ventana
     */
    public void generarVista() {
        ventana = new JFrame("Memorion");
        ventana.setMaximumSize(new Dimension(1924, 1047));
        //ventana.setSize(600, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Método para generar la pantalla de carga, se le pasan las strings del
     * logo
     * y del fondo para crear las imagenes, así como el tiempo que queremos que
     * dure la animación
     * Metodo genera la pantalla Splash,
     * @param logo   Strig,ruta de la imagen que da vueltas
     * @param fondo  String, ruta de la imagen que es asignada en el fondo
     * @param tiempo Int, Tiempo en segundos, indica la duracion del splash.
     */
    private synchronized void cargarSplash(String logo, String fondo, int tiempo) {
       ventana.setSize(600, 600);
        splash = new VistaSplash(logo, fondo, tiempo, fuente, this);
        ventana.setMinimumSize(splash.getMinimumSize());//asignamos el tamaño minimo para la ventana
        
        ventana.add(splash);
        ventana.setVisible(true);
        splash.empezarAnimaciones();
    }


    /**
     * Método que finaliza la ejecución de la vista de carga, además,notificando
     * al hilo principal que estaba en "wait", duerme la ejecucion durante 0.5
     * segundos para no mostrar un cambio brusco, establece el splash a nulo
     * para
     * ahorrar espacio en memoria y elimina la ventana de carg.
     */
    public synchronized void splashTermina() {
        ventana.remove(splash);//lo quitamos de la vusta para que no de errores
        splash = null;//eliminamos el objeto, ya no lo necesitamos mas 
        ventana.repaint();
    }


    public void iniciarJuego() {
        //vJuego.generar();
        //cargarSplash("/img/logotrini.png", "/img/carga.jpg", 0);
        ventana.setSize(1000, 1000);
        ventana.add(vJuego);
        ventana.repaint();
    }


    public void principal() {
       // vPrincipal.generar();
        //cargarSplash("/img/logotrini.png", "/img/carga.jpg", 5);
        
        ventana.setSize(1000, 800);
        ventana.add(vPrincipal);
        ventana.setVisible(true);   
    }
    
    public void estadisticas() {
       // vLista.generar();
        System.out.println("generao cargabdi sspl");
        //cargarSplash("/img/logotrini.png", "/img/carga.jpg", 2);
        System.out.println("paso");
        ventana.setSize(600, 600);
        ventana.add(vLista);
        ventana.setVisible(true);
       // ventana.repaint();
    }

    public void anadirVista(String vista,int tiempo){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                switch (vista) {
                    case "principal":
                        principal();
                        //ventana.setMenuBar(vMenu);
                        //se genera el menu correspondientevMenu.
                        //cargamos vista
                        break;
                    case "ingresodatos":
                        
                        break;
                    case "juego":
                        //ajustar menubar quitar elementos
                        iniciarJuego();
                        break;
                    case "lista":
                        estadisticas();
                        break;
                    case "dialogomodal":
                        break;
                    default:
                        throw new AssertionError();
                }
                
        }};
        timer.schedule(task, tiempo*1000+100);
    }


    public void cambiarVista(String vista) {
        //eliminamos todas las vistas
        eliminarVistas();
        int tiempo=0;
        switch (vista) {
            case "principal":
                //tiempo=5;
                vPrincipal.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                //principal();
                //ventana.setMenuBar(vMenu);
                //se genera el menu correspondientevMenu.
                //cargamos vista
                break;
            case "ingresodatos":
                
                break;
            case "juego":
                //tiempo=4;
                vJuego.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                break;
            case "lista":
               // tiempo=10;
                vLista.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                //estadisticas();
                break;
            case "dialogomodal":
                
                break;
            default:
                throw new AssertionError();
        }
        anadirVista(vista,tiempo);
        
    }


    /**
     * Elimina todas las vistas o paneles añadidos
     */
    public void eliminarVistas() {
        ventana.remove(vPrincipal);
        ventana.remove(vDialogoMod);
        ventana.remove(vIngreso);
        ventana.remove(vJuego);
        ventana.remove(vLista);
        ventana.remove(vPrincipal);
        //ventana.removeAll();
        //vJuego.eliminarElementos();
    }


    public void guardar() {
        try {
            //Creamos un flujo de salida al disco
            FileOutputStream fileOut = new FileOutputStream("juego.obj");
            //Vinculamos el flujo de salida de objetos con el fichero
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);
            //escribimos el objeto
            salida.writeObject(vJuego);
            //cerramos el flujo
            salida.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void cargar() {
        //System.out.println("ewdkjhgbekj");
        //eliminarVistas();
        System.out.println("antes try");
        //FileInputStream fileIn=null;
        try {
            System.out.println("despues try");
            //Creamos un flujo de entrada desde el disco
            FileInputStream fileIn = new FileInputStream("juego.obj");
            //Vinculamos la referencia al disco con nuestro flujo de entrada
            System.out.println("despues file");
            ObjectInputStream entrada = new ObjectInputStream(fileIn);
            //Cargamos el objeto y hacemos el casting del tipo que es
            VJuego vJuegoo = (VJuego) entrada.readObject();
            System.out.println("despues cast");
            ventana.add(vJuegoo);
            System.out.println("despues añadir");
            ventana.setVisible(true);
        } catch (Exception e) {
            System.out.println("edfjn");
            e.printStackTrace();

        }
        vJuego.setVisible(false);

        System.out.println("holas");
    }

}
