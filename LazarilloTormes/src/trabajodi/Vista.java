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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import vista.VPrincipal;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


/**
 *
 * @author Guille
 */
public class Vista {

    private JFrame ventana;
    private Font fuente = new Font("Agency FB", Font.BOLD, 40);
    private EscuchaVentana escuchaVentana;
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
        vPrincipal = new VPrincipal(logica, this);
        vDialogoMod = new VDialogoMod(logica, this);
        vIngreso = new VIngreso(logica, this);
        vJuego = new VJuego(logica, this);
        vLista = new VLista(logica, this);
        vMenu = new VMenu(logica, this);
        //no ponemos splash ya que no necesita logica

        ventana.setVisible(true);
        //temporal
        ventana.setJMenuBar(vMenu);

        // ingresoDatos();
        // estadisticas();
        //principal();
        //iniciarJuego();
        //cambiarVista("juego");
        cambiarVista("principal");
    }


    /**
     * Agrupamos todas las acciones que realizamos sobre la ventana
     */
    public void generarVista() {
        ventana = new JFrame("Memorion");
        ventana.setMaximumSize(new Dimension(1924, 1047));
        //ventana.setSize(600, 600);
        escuchaVentana = new EscuchaVentana(this);
        ventana.addWindowListener(escuchaVentana);
        // ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    private void cargarSplash(String logo, String fondo, int tiempo) {
        ventana.setSize(600, 600);
        splash = new VistaSplash(logo, fondo, tiempo, fuente, this);
        ventana.setMinimumSize(splash.getMinimumSize());//asignamos el tamaño minimo para la ventana
        ventana.add(splash);
        ventana.setVisible(true);
        splash.empezarAnimaciones();
        vMenu.setVisible(false);
    }


    /**
     * Método que finaliza la ejecución de la vista de carga, además,notificando
     * al hilo principal que estaba en "wait", duerme la ejecucion durante 0.5
     * segundos para no mostrar un cambio brusco, establece el splash a nulo
     * para
     * ahorrar espacio en memoria y elimina la ventana de carg.
     */
    public void splashTermina() {
        ventana.remove(splash);//lo quitamos de la vusta para que no de errores
        splash = null;//eliminamos el objeto, ya no lo necesitamos mas 
        ventana.repaint();
        vMenu.setVisible(true);
    }


    public void iniciarJuego() {
        escuchaVentana.setPartidaOn(true);//cambiamos el escuchador para que muestre mensaje antews de salir
        vMenu.modoJuego();
        ventana.setSize(1000, 1000);
        ventana.add(vJuego);
        ventana.repaint();
    }


    public void principal() {
        ventana.setSize(1200, 800);
        ventana.add(vPrincipal);
        ventana.setVisible(true);
    }


    public void iniciarLista() {
        ventana.setSize(1200, 800);
        ventana.add(vLista);
        ventana.repaint();
        ventana.setVisible(true);
    }


    /**
     * Crea la vista de registro, que extiende de panel
     */
    public void crearRegistro() {
        System.out.println("sjf");
        System.out.println("Has entrado en el registro");
        ventana.setSize(1200, 800);
        ventana.add(vIngreso);
        ventana.setVisible(true);
    }


    /**
     * Crea la vista para ver los desarrolladores
     */
    public void crearPantallaDesarrolladores() {
        System.out.println("Has entrado en el registro");
        ventana.setSize(1000, 600);
        ventana.add(vDialogoMod);
        ventana.setVisible(true);
    }


    /**
     *
     *
     *
     *
     * @param vista
     * @param tiempo
     */
    private void anadirVista(String vista, int tiempo) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                switch (vista) {
                    case "principal":
                        principal();
                        //ventana.setMenuBar(vMenu);
                        //se genera el menu correspondientevMenu.
                        //cargamos vista
                        break;

                    case "ingresodatos":

                        System.out.print("INGRESO DATOS");

                        crearRegistro();

                        break;

                    case "juego":
                        //ajustar menubar quitar elementos
                        iniciarJuego();
                        break;

                    case "juegoguardado":
                        iniciarJuego();
                        break;

                    case "lista":
                        iniciarLista();
                        break;

                    case "aboutus":
                        crearPantallaDesarrolladores();
                        break;

                    default:
                        System.out.print(vista);
                        throw new AssertionError();

                }

                ventana.repaint();
                ventana.setVisible(true);
            }
        };
        timer.schedule(task, tiempo * 1000 + 200);
    }


    /*
     * VIENE DE VISTAPRINCIPAL
     * VIENE DE VISTAPRINCIPAL
     * VIENE DE VISTAPRINCIPAL
     * VIENE DE VISTAPRINCIPAL
     * VIENE DE VISTAPRINCIPAL
     */
    public void cambiarVista(String vista) {
        //eliminamos todas las vistas
        eliminarVistas();
        int tiempo = 1;
        switch (vista) {
            case "principal":
                tiempo = 1;
                vPrincipal.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                //principal();
                //ventana.setMenuBar(vMenu);
                //se genera el menu correspondientevMenu.
                //cargamos vista
                break;

            case "ingresodatos":
                tiempo = 1;

                vIngreso.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);

                break;

            case "juego":
                tiempo = 1;
                vJuego.generar();//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                break;

            case "juegoguardado":
                tiempo = 1;
                vJuego.generarGuardada();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                break;

            case "lista":
                tiempo = 1;
                vLista.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                //estadisticas();
                break;

            case "aboutus":
                tiempo = 1;
                vDialogoMod.generar();
                cargarSplash("/img/logotrini.png", "/img/carga.jpg", tiempo);
                break;

            default:
                throw new AssertionError();
        }
        anadirVista(vista, tiempo);
    }


    /**
     * Elimina todas las vistas o paneles añadidos
     */
    public void eliminarVistas() {
        escuchaVentana.setPartidaOn(false);
        ventana.remove(vPrincipal);
        vPrincipal.removeAll();
        ventana.remove(vDialogoMod);
        vDialogoMod.removeAll();
        ventana.remove(vIngreso);
        vIngreso.removeAll();
        ventana.remove(vJuego);
        vJuego.removeAll();
        ventana.remove(vLista);
        vLista.removeAll();
        ventana.remove(vPrincipal);
        vPrincipal.removeAll();
        //ventana.removeAll();
        //vJuego.eliminarElementos();
    }

    
    public void avisoSalida() {
        
    }//this.getClass().getResource("/img/despedida.gif"
    public void splashScreen() {
        
    }


    class EscuchaVentana extends WindowAdapter {

        private boolean partidaOn = false;
        Vista vista;


        public EscuchaVentana(Vista vista) {
            this.vista = vista;
        }


        @Override
        public void windowClosing(WindowEvent e) {
            if(partidaOn){
                if (JOptionPane.showConfirmDialog(ventana, "You will exit off the game ¿Are you sure?","Do you want to exit?", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
                ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }else{
                    System.exit(0);
                } 
            }else{
                System.exit(0);
            }
        }


        public void setPartidaOn(boolean partidaOn) {
            this.partidaOn = partidaOn;
        }

    }

}
/*class wi
/* @Override
    public void windowClosing(WindowEvent e) {
       if (JOptionPane.showConfirmDialog(vista, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }*/
