/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;


import controladores.ContrIngreso;
import controladores.ContrJuego;
import controladores.ContrLista;
import controladores.ContrMenu;
import controladores.ControladorPrincipal;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Guille
 */
public class Logica {

    private ContrJuego juego;
    private ControladorPrincipal principal;
    private ContrLista lista;
    private ContrMenu menu;
    private PartidaGuardada partidaGuardada;
    private boolean primeraJuego, animacionC;
    private Timer timer;
    private int vuelta, cartaAct;
    private final String FICHERO = "estadisticas.txt", PRIMERA_LINEA = "Directorio de almacenamiento de estadistica\n",RUTA_SONIDO_MAIN="/sonidos/",RUTA_IMAGENES="/img/";
    private String[] rutas;

    private String nombre,avatar;
    private boolean sonido = true;


    public Logica() {
        //crearFichero(true);
        crearSonido();
    }


    /*
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     * CONTROLADOR JUEGO
     */
    /**
     * asinamos el controlador de Juego y incicializamos valores a un juego por
     * defecto
     * @param juego ContrJuego encargado de la comunicacion con el juego
     */
    public void asignarContrJuego(ContrJuego juego) {
        this.juego = juego;
        primeraJuego = true;
        animacionC = false;
    }


    /**
     * Encargado de gestionar las acciones de de mouseListener del controlador
     * del juego, aqui se gestionaran toda la logica
     * @param componente
     */
    public synchronized void juegoClick(Component componente) {
        //si se ejecuta laguna accion  comienza el contador
        if (primeraJuego) {
            juego.gestionarContador("empezar");
            primeraJuego = false;//ya no es la 1 accion del juego
        }

        if (componente instanceof JButton) {//si es un boton
            accionBotonJuego((JButton) componente);

        } else if (componente instanceof JLabel) {//si es un JLabel, 
            accionLabelJuego(Integer.parseInt(componente.getName()));
        }
    }


    /**
     * Metodo que controla las accciones de la carta, recive la carta que tiene
     * la accion
     * @param carta int indice de la carta
     */
    private void accionLabelJuego(int carta) {
        if (!animacionC) {//mientras que no tengamos ninguna animacion en progreso
            vuelta = juego.algunaVisible();//antes de mover la carta actual miramos si hay alguna carta visible
            cartaAct = carta;//opbtenemos el indice del array de la carta que ss hizo click

            if (vuelta == cartaAct) {//comprobamos que no tenga hecho click en la misma imagen 2 veces
                vuelta = -1;//si es asi la carta que se dio la vuelta no existe, para la logica
            }
            juego.movimiento();//se realiza 1 movimiento, sumamos 1 al contador

            juego.girar(cartaAct);//gira mos la carta que se hizo click

            if (vuelta != -1) {//si es diferente de  -1 hay 2 visibles
                if (juego.mismaImagen(vuelta, cartaAct)) {//si las cartas que hay son iguales

                    juego.bloquearImagenes(vuelta, cartaAct);//bloqueamos la imagen, para que no se puedan mover mas

                    if (!juego.isFin()) {//si es el fin del juego(Si a termiando)
                        juego.gestionarContador("pausa");
                        //cambiamos los estados de los botones 
                        juego.cambiarEstadoBoton("guardar", false);
                        juego.cambiarEstadoBoton("playPause", false);
                        juego.cambiarEstadoBoton("continuar", true);
                    }

                } else {//si las cartas que tenemos no son iguales las giramos
                    animacionC = true;//ponermos que existe una animacion en progreso
                    timer = new java.util.Timer();//cremos el timer para crear una animacion
                    TimerTask tarea = new TimerTask() {//creamos un timerTask, que se ejecutara en x segundos
                        int i = 0;
                        @Override
                        public void run() {
                            if (i > 0) {//cuando termine dejamos hacer otra accion, para que no se vean 3 imagenes
                                animacionC = false;//marcamos  que el timer termino
                                timer.cancel();
                                timer = null;
                            } else {
                                i++;
                                juego.girar(cartaAct);//girampos la carta actual 

                                juego.girar(vuelta);//girampos la que esta visble
                                //   timer = null;//eliminamos el timer
                            }

                        }
                    };
                    //asignamos que se mueva en estos seguntos para que no cambie de golpe , para que la animacion sea mas suave
                    timer.schedule(tarea, 1000, 1000);// decimos al timer que ejecute el TimeTask en los seguntos
                }
            }
        }
    }


    private void accionBotonJuego(JButton boton) {
        if (boton.isEnabled()) {
            switch (boton.getActionCommand()) {
                case "playPause":
                    juego.gestionarContador(boton.getActionCommand());
                    break;

                case "continuar"://guardar los datos
                    guardarDatos();//guardamos los datos y los mostramos
                    juego.cambiarVista("lista");
                    break;

                case "guardar":
                    System.out.println("en creaccion");
                    break;

                default:
                    System.out.println("error juegoClick opcion no esperada: " + boton.getActionCommand());
            }
        }
    }


    public void juegokey(char pulso) {
        switch (pulso) {
            case 'q':
                //...
                break;
            default:
                throw new AssertionError();
        }
    }


    public String[] obtenerRutasImg() {
        //inicializamos temporal
        rutas = new String[2];
        rutas[0] = "/img/carta.jpg";
        rutas[1] = "/img/2.jpg";
//        rutas[2] = "/img/carga2.jpg";
//        rutas[3] = "/img/flecha.png";
//        rutas[4] = "/img/flecha.png";
//        rutas[5] = "/img/flecha.png";
//        rutas[6] = "/img/flecha.png";
//        rutas[7] = "/img/flecha.png";
        return rutas;
        //rutas=null;
    }


    /*
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     * CONTROLADOR MENU
     */
    public void asignarMenu(ContrMenu menu) {
        this.menu = menu;
    }


    public void gestionarMenu(String accion) {

        switch (accion) {
            case "cargar":
                cargarPartida();
                menu.cambiarVista("juegoguardado");
                break;
            case "guardar":
                guardarPartida();
                break;
            case "salir":
                if (JOptionPane.showConfirmDialog(null, "You will exit off the game ¿Are you sure?", "Do you want to exit?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                System.out.println(accion);
                break;
            case "atras":
                System.out.println(accion);
                break;
            case "pausaplay":
                System.out.println(accion);
                break;
            case "sonido":
                sonido = menu.estadoSonido();
                System.out.println(accion);
                break;
            case "partidarapida"://Partida Rapida
                guardarPartida();
                break;
            case "partidapersonalizada"://Partida Personalizada
                guardarPartida();
                break;
            default:
                System.out.println("Valor no esperado en logica gestionarMenu: " + accion);
        }
    }


    private void guardarDatos() {
        //nombre//ya lo tengo 
        //imagen //ya lo tengo
        //movimientos
        juego.getContMov();
        //tiempo
        juego.getContadorSeg();
    }


//    private void gestionFichero(Historial historialNuevo){
//        ArrayList<Historial> historial;
//        //nos aseguramos que existe un fichero
//        crearFichero(true);
//        //añadimos el contenido del fichero a un array
//        historial= pasarFicheroAArray();
//        //añadimos los nuevos valores 
//        historial.add(historialNuevo);
//        //ordenamos el arraylist
//        Collections.sort(historial);
//        //lo escribimos en el fichero
//        pasarAFichero(historial);
//    }
    private void crearFichero(boolean mantenerFichero) {
        try {
            File archivo=new File(new File("test.txt").getAbsolutePath());
           
            //System.out.println(aux.getAbsoluteFile());
                    
           // System.out.println(fichero2.getAbsoluteFile().getPath());
          //  File archivo = new File(this.getClass().getName().);
            System.out.println("sajfkb");
          //  System.out.println(archivo.getAbsoluteFile());
           if (!archivo.exists()) {
                FileWriter escritor = new FileWriter(archivo, mantenerFichero);//true no sobrescribe
                escritor.write(PRIMERA_LINEA);
                escritor.close();
            }
        } catch (Exception e) {
            System.out.println("Error al escribir");//Si existe un problema al escribir cae aqui
        }
    }


    public void mostrarFichero() {
        //Creamos un String que va a contener todo el texto del archivo
        String texto = "";
        System.out.println("ficheroooooo");
        System.out.println("================================");
        try {
            //Creamos un archivo FileReader que obtiene lo que tenga el archivo
            FileReader lector = new FileReader(FICHERO);

            //El contenido de lector se guarda en un BufferedReader
            BufferedReader contenido = new BufferedReader(lector);

            //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
            while ((texto = contenido.readLine()) != null) {
                System.out.println(texto);
            }
        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
        }
    }


    public void test() {
        ArrayList historial = new ArrayList<Historial>();
        //String nombre, ImageIcon imagen, int tiempo, int movimientos
        historial.add(new Historial(20, 10, "url", "em"));
        historial.add(new Historial(50, 15, "url", "am"));
        historial.add(new Historial(20, 15, "url", "om"));
        historial.add(new Historial(2, 10, "url", "ma"));
        historial.add(new Historial(50, 18, "url", "me"));
        pasarAFichero(historial);
        /*
         * for (Object object : historial) {
         * System.out.println(object.toString()+"");
         * }
         * Collections.sort(historial);
         * System.out.println("----");
         * for (Object object : historial) {
         * System.out.println(object.toString()+"");
         * }
         */
    }


    public ArrayList<Historial> pasarFicheroAArray() {//Retorna el contenido del fichero en el array
        String linea;
        String[] lineas;
        ArrayList<Historial> historial = new ArrayList();
        try {
            FileReader f = new FileReader(FICHERO);
            BufferedReader b = new BufferedReader(f);
            b.readLine();//ignoramos la 1º linea
            while ((linea = b.readLine()) != null) {
                lineas = linea.split(";");//movimientos;tiempo;imagen;nombre
                historial.add(new Historial(Integer.parseInt(lineas[0]), Integer.parseInt(lineas[1]), lineas[2], lineas[3]));
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return historial;
    }


    public void pasarAFichero(ArrayList<Historial> historial) {//coge el arrayList y lo escribe en el fichero
        try {
            FileWriter fichero = new FileWriter(FICHERO, false);
            fichero.write(PRIMERA_LINEA);
            for (Historial historiales : historial) {
                fichero.write(historiales.toString() + "\n");
            }
            fichero.close();
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }

    }


    public String[] ficheroAArray() {
        String linea;
        ArrayList<String> lineas = new ArrayList();
        try {
            FileReader f = new FileReader(new File(FICHERO).getAbsolutePath());
            BufferedReader b = new BufferedReader(f);
            b.readLine();//ignoramos la 1º linea
            while ((linea = b.readLine()) != null) {
                lineas.add("" + linea);//movimientos;tiempo;imagen;nombre
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for (String linea1 : lineas) {
//            System.out.println(linea1);
//        }
//        System.out.println("==========================");
        return lineas.toArray(new String[lineas.size()]);
    }


    private ContrIngreso cIngreso;


    public void asignarControladorIngreso(ContrIngreso ingreso) {
        this.cIngreso = ingreso;
    }


    public void vistaIngresoMouseListener(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "botonFlechaAtras":
                cIngreso.cambiarVista("principal");
                break;
            case "botonFlechaSiguiente":
                cIngreso.mandarRecogerDatos();
                cIngreso.cambiarVista("juego");
                break;
            case "avatar":
                cogerImagenSistema();
                break;
            default:
                System.err.println("\nOpcion no valida");
        }
    }
    
    public void recogerDatos(String avatar,int tema, int dificultad, String nombre){
        //guardamos avatar  y nombre
        this.nombre=nombre;
        this.avatar=avatar;

        //añadimos cartas segun la dificultad; siendo la minima 4 cartas
        cartasSegunDificultad(dificultad);
        
        //añadimos las images
        cargarRutas(tema);
        
    }
    private void cartasSegunDificultad(int dificultad){
        int cartas=2;
        for (int i = dificultad; i >0; i--) {
            cartas+=2;
        }
        rutas = new String[cartas];
    }
    private void cargarRutas(int tema){
        String rutaConTema=RUTA_IMAGENES+"cartas/tema"+tema;
        for (int i = 0; i < rutas.length; i++) {
            rutas[i] = rutaConTema+i;
        }
    }


    public void vistaIngresoItemChange(ItemEvent e) {
        int avatar = 1, tema = 1, dificultad = 1;
        if (((JCheckBox) e.getSource()).isSelected()) {
            String variableTexto = ((JCheckBox) e.getSource()).getName();
            switch (variableTexto) {
                case "avatar1":
                case "avatar2":
                case "avatar3":
                    avatar = Integer.parseInt(variableTexto.substring(6));
                    cIngreso.asignarBordeAvatar(avatar);
                    break;
                case "tema1":
                case "tema2":
                case "tema3":
                    tema = Integer.parseInt(variableTexto.substring(4));
                    cIngreso.asignarBordeTema(tema);
                    break;
                case "dificultad1":
                case "dificultad2":
                case "dificultad3":
                    dificultad = Integer.parseInt(variableTexto.substring(10));
                    cIngreso.asignarBordeDificultad(dificultad);
                    break;
                default:
                    System.err.println("\nOpcion no valida");
            }
        }
    }


    public void vistaIngresoEscuchadorTexto(TextEvent e) {
//        switch (e.) {
//            case "campoNombre":
//                System.out.print("añksljfñlaskjfsñ");
//
//                break;
//
//            default:
//                System.err.println("\nOpcion no valida");
//        }
    }

    ///////////////////////////

    public void guardar() {
        try {
            //Creamos un flujo de salida al disco
            FileOutputStream fileOut = new FileOutputStream("juego.obj");
            //Vinculamos el flujo de salida de objetos con el fichero
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);
            //escribimos el objeto
            //   salida.writeObject(vJuego);
            //cerramos el flujo
            salida.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void guardarPartida() {
        //int segundos, int movimientos, int vuelta, ArrayList<String> rutaGuardada, ArrayList<Boolean> cartaBloqueada
        PartidaGuardada partida = new PartidaGuardada(juego.getContadorSeg(), juego.getContMov(), juego.algunaVisible(), juego.guardarUrlCarta(), juego.guardarBloquearCarta());

//        PartidaGuardada partida = new PartidaGuardada();
//        partida.setRutaGuardada(juego.guardarUrlCarta());
//        partida.setCartaBloqueada(juego.guardarBloquearCarta());
//        partida.setSegundos(juego.getContadorSeg());
//        partida.setMovimientos(juego.getContMov());
//        partida.setVuelta(juego.algunaVisible());
        try {
            //Creamos un flujo de salida al disco
            FileOutputStream fileOut = new FileOutputStream("PartidaGuardada.obj");
            //Vinculamos el flujo de salida de objetos con el fichero
            ObjectOutputStream salida = new ObjectOutputStream(fileOut);
            //escribimos el objeto
            salida.writeObject(partida);
            //cerramos el flujo
            salida.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void cargarPartida() {
        partidaGuardada = null;
        try {
            //Creamos un flujo de entrada desde el disco
            FileInputStream fileIn = new FileInputStream("PartidaGuardada.obj");
            //Vinculamos la referencia al disco con nuestro flujo de entrada
            ObjectInputStream entrada = new ObjectInputStream(fileIn);
            //Cargamos el objeto y hacemos el casting del tipo que es
            partidaGuardada = (PartidaGuardada) entrada.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }

        rutas = partidaGuardada.getRutaGuardada().toArray(new String[partidaGuardada.getRutaGuardada().size()]);
        for (String ruta : rutas) {
            System.out.println(ruta);
        }
        //generamos la partida
    }


    public int obtenerGuardadDesact() {
        int desactivadas = 0;
        ArrayList<Boolean> cartaBloqueada = partidaGuardada.getCartaBloqueada();
        for (int i = 0; i < cartaBloqueada.size(); i++) {
            if (cartaBloqueada.get(i)) {//si bloquear == true esta desactivada
                desactivadas++;
            }
        }
        return desactivadas;
    }


    public void bloquearCartas() {
        ArrayList<Boolean> cartaBloqueada = partidaGuardada.getCartaBloqueada();
        for (int i = 0; i < cartaBloqueada.size(); i++) {
            if (cartaBloqueada.get(i)) {//si bloquear == true esta desactivada
                juego.bloquearUna(i);
                //juego.girar(i);
            }
        }
    }


    public int obtenerMovimientos() {
        System.out.println("ewrwdgjbsg" + partidaGuardada.getMovimientos());
        if (partidaGuardada.getVuelta() == -1)//si no hay ninguna carta dada la vuelta retornamos esto
        {
            return partidaGuardada.getMovimientos();
        } else {
            juego.girar(partidaGuardada.getVuelta());
            return partidaGuardada.getMovimientos() - 1;
        }
    }


    public int obtenerTiempo() {
        return partidaGuardada.getSegundos();
    }
//    public int obtenerVuelta(){
//        return partidaGuardada.getVuelta();
//    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //controlador Estadisticas(vLista)
    public void asignarContrLista(ContrLista lista) {
        this.lista = lista;
    }


    public void listaClick(Component componente) {
        if (componente instanceof JButton) {
            JButton bPulsado = (JButton) componente;
            if (bPulsado.getToolTipText().equals("Go to main")) {
                lista.cambiarVista("principal");
            }
        } else {
            System.out.println("evento no esperado en lista");
        }
    }
///


    public void asignarContrPrincipal(ControladorPrincipal principal) {
        this.principal = principal;
    }


    /*
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     * // VISTA PRINCIPAL
     */
    public void principalClick(String boton, int pulsos) {
        switch (boton.replaceAll(" ", "").toLowerCase()) {
            case "newgame":
                principal.cambiarDeVista("ingresodatos");
                System.out.println("1");
                break;
            case "loadgame":
                System.out.println("2");
                break;
            case "stats":
                principal.cambiarDeVista("lista");
                break;
            case "aboutus":
                principal.cambiarDeVista("aboutus");
                System.out.println("4");
                break;
            case "":
                System.out.println("5");
                break;
            default:
                throw new AssertionError();
        }
    }


    /*
     * COGER IMAGEN SISTEMA
     * COGER IMAGEN SISTEMA
     * COGER IMAGEN SISTEMA
     * COGER IMAGEN SISTEMA
     * COGER IMAGEN SISTEMA
     * COGER IMAGEN SISTEMA
     */
    private JFileChooser archivoSeleccionado;
    private FileNameExtensionFilter filtroArchivos;
    private int resultado;


    /**
     * Le asigna la ruta a una variable string y se la pasa por parametro al
     * metodo que asigna la foto
     */
    public void cogerImagenSistema() {
        String path;
        selecionarYFiltrarArchivos();
        if (resultado == JFileChooser.APPROVE_OPTION) {
            path = archivoSeleccionado.getSelectedFile().getAbsolutePath();
//            controlApp.vistaAppControlador.asignarFotoalBoton(path);
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            System.out.println("No has seleccionado ningun archivo");
        }
    }


    /**
     * Abre el explorador y filtra los archivos que aparecen cuando se abre el
     * explorador
     */
    public void selecionarYFiltrarArchivos() {
        archivoSeleccionado = new JFileChooser();
        archivoSeleccionado.setCurrentDirectory(new File(System.getProperty("user.dir")));
        filtroArchivos = new FileNameExtensionFilter(".jpg", ".gif", ".png");
        archivoSeleccionado.addChoosableFileFilter(filtroArchivos);
//        archivoSeleccionado.setFileFilter(filtroArchivos);
        resultado = archivoSeleccionado.showSaveDialog(null);
    }


    /*
     * SONIDO
     * SONIDO
     * SONIDO
     * SONIDO1
     * SONIDO
     */

    public void crearSonido() {
        if(sonido){
            String accion="correcta";//viene del main
            String rutaSonido=RUTA_SONIDO_MAIN+accion+((int) (Math.random()*3)+1)+".wav";
            Sonido reproducir = new Sonido(rutaSonido);
            reproducir.start();
            System.out.println("Después de la canción");
        }
    }
}
