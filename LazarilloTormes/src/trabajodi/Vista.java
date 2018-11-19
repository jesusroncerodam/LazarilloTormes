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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        vMenu = new VMenu(logica,this);
        //no ponemos splash ya que no necesita logica
        
        ventana.setVisible(true);
        //temporal
        ventana.setJMenuBar(vMenu);

       ingresoDatos();
        //estadisticas();
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
        ventana.setSize(600,600);
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
        ventana.setSize(600, 600);        
        ventana.add(vLista);
        ventana.setVisible(true);
        ventana.repaint();
    }
    
    
    public void cambiarVista(String vista){
        //eliminamos todas las vistas
        eliminarVistas();
        switch (vista) {
            case "":
                //ventana.setMenuBar(vMenu);
                //se genera el menu correspondientevMenu.
                //cargamos vista
                break;
            default:
                throw new AssertionError();
        }
    }
    /**
     * Elimina todas las vistas o paneles añadidos 
     */
    public void eliminarVistas(){
        for (int i = 0; i <  ventana.getComponentCount(); i++) {
            ventana.remove(i);
        }
        ventana.removeAll();
        vJuego.eliminarElementos();
    }
    public void guardar(){
        try {
            //Creamos un flujo de salida al disco
            FileOutputStream fileOut = new FileOutputStream("juego.obj");
            //Vinculamos el flujo de salida de objetos con el fichero
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
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
    public void cargar(){
        //System.out.println("ewdkjhgbekj");
        eliminarVistas();
        
        //FileInputStream fileIn=null;
        try {
            //Creamos un flujo de entrada desde el disco
            FileInputStream fileIn = new FileInputStream("juego.obj");
            //Vinculamos la referencia al disco con nuestro flujo de entrada
            ObjectInputStream entrada=new ObjectInputStream(fileIn);
            //Cargamos el objeto y hacemos el casting del tipo que es
            VJuego vJuegoo=(VJuego)entrada.readObject();
            ventana.add(vJuegoo);
            ventana.repaint();
            ventana.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
