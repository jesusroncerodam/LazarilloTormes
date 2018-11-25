package trabajodi;


import controladores.ContrDialogoMod;
import controladores.ContrIngreso;
import controladores.ContrJuego;
import controladores.ContrLista;
import controladores.ContrMenu;
import controladores.ControladorPrincipal;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
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
    private ContrIngreso cIngreso;
    private ContrDialogoMod cDialogMod;

    private PartidaGuardada partidaGuardada;
    private boolean primeraJuego, animacionC, partidaCargadaOn = false;
    private Timer timer;
    private int vuelta, cartaAct;
    private String[] rutas;
    private String nombre, avatar;
    private boolean sonido = true;

    private final String FICHERO = "estadisticas.txt",
            PRIMERA_LINEA = "Directorio de almacenamiento de estadistica\n",
            RUTA_SONIDO_MAIN = "/sonidos/",
            RUTA_IMAGENES = "/img/",
            ARCHIVO_PARTIDA_GUARDADA = "PartidaGuardada.obj",
            RUTA_TEMA = "cartas/tema";
    private final boolean ELIMINAR_PARTIDA_GUARDADA = false;


    /**
     * Constructor de logia, no se manda nada, se crea al comienzo de la
     * ejecucion
     * al llamarlo se ejecutara un sonido
     */
    public Logica() {
        crearSonido("inicio");//cargamos el sonido para el inicio
    }


    /*
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
    public void juegoClick(Component componente) {
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
            crearSonido("movimiento");//cargamos el sonido para la accion
            juego.girar(cartaAct);//gira mos la carta que se hizo click

            if (vuelta != -1) {//si es diferente de  -1 hay 2 visibles
                if (juego.mismaImagen(vuelta, cartaAct)) {//si las cartas que hay son iguales

                    juego.bloquearImagenes(vuelta, cartaAct);//bloqueamos la imagen, para que no se puedan mover mas
                    crearSonido("correcta");//cargamos el sonido para la accion

                    if (!juego.isFin()) {//si es el fin del juego(Si a termiando)
                        partidaCargadaOn = false;//ajustamos siempre a false
                        crearSonido("victoria");//cargamos el sonido para la accion
                        juego.setVictoria(true);
                        juego.gestionarContador("pausa");//pausamos el contador
                        //cambiamos los estados de los botones 
                        juego.cambiarEstadoBoton("guardar", false);
                        juego.cambiarEstadoBoton("playPause", false);
                        juego.cambiarEstadoBoton("continuar", true);
                        guardarDatos();//guardamos los datos y los mostramos
                    }

                } else {//si las cartas que tenemos no son iguales las giramos
                    crearSonido("fallo");//cargamos el sonido para la accion
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


    /**
     * Metodo controla las acciones que se realizan mediante el pulso de un
     * boton,
     * en la vista del juego
     * @param boton boton que recive la accion
     */
    private void accionBotonJuego(JButton boton) {
        if (boton.isEnabled()) {
            switch (boton.getActionCommand()) {
                case "playPause":
                    juego.gestionarContador(boton.getActionCommand());
                    break;

                case "continuar"://guardar los datos
                    //guardarDatos();//los datos los guardamos al ternimar la partida
                    juego.cambiarVista("lista");
                    break;

                case "guardar":
                    guardarPartida();//guardamso la partida
                    juego.cambiarVista("principal");//mandamos al main
                    break;

                default:
                    System.out.println("error juegoClick opcion no esperada: " + boton.getActionCommand());
            }
        }
    }


    /**
     * Metodo obtiene la tecla pulsada en la vista del juego
     * @param pulso char de la letra que se pulsa
     */
    public void juegokey(char pulso) {
        int indice = 0;
        pulso = Character.toUpperCase(pulso);
        //lo pasamos a mayusculas siempre, a mayusculas y no a minisculas ya que
        //las mallusculas tienen un indice inferior en ASCII
        indice = ((int) pulso) - 65;
        if (indice < (rutas.length * 2) || (partidaCargadaOn && indice < rutas.length)) {
            accionLabelJuego(indice);
        }
    }


    /**
     * Retorna las rutas que han sido asignadas, en funcion de la dificultad,
     * tema y/o si se carga una partida .
     * @return Array de String con rutas de imagenesS
     */
    public String[] obtenerRutasImg() {
        return rutas;
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
    
    /**
     * Asigna el controlador del menu a la logica
     * @param menu COntrolador del menu
     */
    public void asignarMenu(ContrMenu menu) {
        this.menu = menu;
    }


    /**
     * Gestiona las acciones que son mandadas a traves del menu
     * @param accion String con la accion correspondiente
     */
    public void gestionarMenu(String accion) {
        switch (accion) {
            case "cargar":
                if (cargarPartida()) {
                    menu.cambiarVista("juegoguardado");
                }
                break;
            case "guardar":
                guardarPartida();
                menu.cambiarVista("principal");
                break;
            case "salir":
                if (JOptionPane.showConfirmDialog(null, "You will exit off the game ¿Are you sure?", "Do you want to exit?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
            case "atras":
                menu.cambiarVista("principal");
                break;
            case "pausaplay":
                juego.gestionarContador("playpause");
                break;
            case "sonido":
                sonido = menu.estadoSonido();
                break;
            case "partidarapida"://Partida Rapida /////////////////////////////////////////////////////////////////////////////////////////////////////
                guardarPartida();
                break;
            case "partidapersonalizada"://Partida Personalizada////////////////////////////////////////////////////////////////////////////////////////////////////////////
                menu.cambiarVista("ingresodatos");
                break;
            case "reset":
                if (JOptionPane.showConfirmDialog(null, "You will reset statistics  ¿Are you sure?", "Do you want to reset?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    eliminarFichero(FICHERO);
                }
                break;
            default:
                System.out.println("Valor no esperado en logica gestionarMenu: " + accion);
        }
    }


    /**
     * Metodo guarda los datos en el fichero, de las estadisticas y lo ordena
     */
    private void guardarDatos() {
        crearFichero();//nos aseguramso de que exista un fichero
        ArrayList<Historial> historial = pasarFicheroAArray();
        historial.add(new Historial(juego.getContMov(), juego.getContadorSeg(), avatar, nombre));//añadimos
        //ordenamos
        Collections.sort(historial);
        //eliminamos el fichero anterior 
        eliminarFichero(FICHERO);
        crearFichero();//volvemos a crear el fichero
        pasarAFichero(historial);//le mandamis informacion
    }


    /**
     * Encargado de eliminar el fichero de estadistica
     */
    private void eliminarFichero(String archivo) {
        File file = new File(new File(archivo).getAbsolutePath());
        file.delete();
    }


    /**
     * Si el Fichero no existe lo crea, con la primera linea qye corresponde a
     * al string PRIMERA_LINEA
     */
    private void crearFichero() {
        try {
            File archivo = new File(new File(FICHERO).getAbsolutePath());
            if (!archivo.exists()) {
                FileWriter escritor = new FileWriter(archivo, true);//true no sobrescribe
                escritor.write(PRIMERA_LINEA);
                escritor.close();
            }
        } catch (Exception e) {
            System.out.println("Error al escribir");//Si existe un problema al escribir cae aqui
        }
    }


    /**
     * Metodo pasa el fichero a un arrayList
     * @return retodna un array list de historial
     */
    private ArrayList<Historial> pasarFicheroAArray() {//Retorna el contenido del fichero en el array
        String linea;
        String[] lineas;
        ArrayList<Historial> historial = new ArrayList();
        try {
            // FileReader f = new FileReader(FICHERO);            
            FileReader f = new FileReader(new File(FICHERO).getAbsolutePath());
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


    /**
     * Metodo pasa un arrayList de historial al fichero, ignorando la 1 linea
     * @param historial Arraylist de historial a escribir en el fichero
     */
    public void pasarAFichero(ArrayList<Historial> historial) {//coge el arrayList y lo escribe en el fichero
        try {
            FileWriter fichero = new FileWriter((new File(FICHERO).getAbsolutePath()), false);
            fichero.write(PRIMERA_LINEA);
            for (Historial historiales : historial) {
                fichero.write(historiales.toString() + "\n");
            }
            fichero.close();
        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
    }


    /**
     * Metodo pasa el fichero a un array de string usando el metodo "toString"
     * en Historial. usado por el controlador de VLista
     * @return Array de sting de Historial
     */
    public String[] ficheroAArray() {
        String linea;
        ArrayList<String> lineas = new ArrayList();
        if (!new File(FICHERO).exists()) {
            JOptionPane.showMessageDialog(null, "There are no statistics.", "statistics not found", JOptionPane.WARNING_MESSAGE);
            return lineas.toArray(new String[lineas.size()]);
        }
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
        return lineas.toArray(new String[lineas.size()]);
    }


    /*
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     * CONTROLADOR VISTA INGRESO
     */
    /**
     * Metodo que iguala el controlador local con el original
     * @param ingreso Controlador que se pasa para igualarlo
     */
    public void asignarControladorIngreso(ContrIngreso ingreso) {
        this.cIngreso = ingreso;
    }


    /**
     * Metodo encargado de escuchar los clicks del raton y ver el lugar en el
     * que se esta pulsando
     * @param e evento que ve que objeto es
     */
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
                cIngreso.establecerImagenElegida(cogerImagenSistema());
                break;
            default:
                System.err.println("\nOpcion no valida");
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
    /**
     * Le asigna la ruta a una variable string y se la pasa por parametro al
     * metodo que asigna la foto
     * @return String, es el path de la imagen elegida en ruta absoluta
     */
    public String cogerImagenSistema() {
        JFileChooser archivoSeleccionado = new JFileChooser();
        archivoSeleccionado.setCurrentDirectory(new File(System.getProperty("user.dir")));
        FileNameExtensionFilter filtroArchivos = new FileNameExtensionFilter(".jpg", ".gif", ".png");
        archivoSeleccionado.addChoosableFileFilter(filtroArchivos);
//        archivoSeleccionado.setFileFilter(filtroArchivos);
        String path = null;
        int resultado = archivoSeleccionado.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            path = archivoSeleccionado.getSelectedFile().getAbsolutePath();
        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            System.out.println("No has seleccionado ningun archivo");
        }
        return path;
    }


    /*
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     * CONTROLADOR VISTA DIALOGMOD
     */
    public void asignarControladorDialogoMod(ContrDialogoMod cDialogo) {
        this.cDialogMod = cDialogo;
    }


    /**
     * Escucha los clicks del raton y ejecuta la accion del lugar que estas
     * pulsando
     * @param e evento encargado de ver que componente es
     */
    public void vistaDialogoModMouseListener(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "iconoTrinitarias":
                try {
                    Desktop.getDesktop().browse(new URI("https://www.trinitarias.com/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                System.err.println("\nOpcion no valida");
        }
    }


    /**
     * Escucha los actionevent que ocurren en la vista de DialogMod, de momento
     * solo hay uno, el del boton de vuelta atras
     * @param e evento que ocurre cuando pulsas el boton
     */
    public void vistaDialogoModActionListener(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "botonAtras":
                cDialogMod.cambiarVista("principal");
                break;
            default:
                System.err.println("\nOpcion no valida");
        }
    }
    
    
    /**
     * Metodo encargado de gestionar los datos que se recogen de la
     * vista de Ingreso
     * @param avatar     String ruta del avatar
     * @param tema       int 1,2 o 3, tema de la partida, de las cartas
     * @param dificultad int 1,2 o 3 dificultad, cantidad de cartas a mostrar
     * @param nombre     String nombre del jugador
     */
    public void recogerDatos(String avatar, int tema, int dificultad, String nombre) {
        //guardamos avatar  y nombre

        this.nombre = gestionarNombre(nombre);
        this.avatar = avatar;

        //añadimos cartas segun la dificultad; siendo la minima 4 cartas
        cartasSegunDificultad(dificultad);

        //añadimos las images
        cargarRutas(tema);
    }


    /**
     * Metodo gestiona que el nombre no sea muy grande y que no sea el
     * de por defecto
     * @param nombre String nombre elegido por el usuario
     */
    private String gestionarNombre(String nombre) {
        if (nombre.toLowerCase().equals("nickname")) {
            nombre = "Gamer";
        }
        if (nombre.length() > 15) {
            nombre = nombre.substring(0, 15);
        }
        return nombre;
    }


    /**
     * Metodo encargado de crear un Array de String con respecto a la dificultad
     * correspondiente, por cada nivel de dificultad suma 2 cartas, empezando
     * por un minimo de 2 cartas
     * @param dificultad int que asigna la dificultad
     */
    private void cartasSegunDificultad(int dificultad) {
        int cartas = 2;
        for (int i = dificultad; i > 0; i--) {
            cartas += 2;
        }
        rutas = new String[cartas];
    }


    /**
     * Metodo encargado de segun el tema carga las rutas de las imagenes
     * correspondientes en el arrat de string
     * @param tema int del 1 al 3 , dificultad de la carta
     */
    private void cargarRutas(int tema) {
        if (tema > 3 || tema < 1) {//realizamos una comprobacion
            tema = 1;
        }
        String rutaConTema = RUTA_IMAGENES + RUTA_TEMA + tema + "/";
        for (int i = 0; i < rutas.length; i++) {
            rutas[i] = rutaConTema + i + ".jpg";
        }
    }


    /**
     * Metodo que escucha los CheckBox de avatar, tema y dificultad, y llama a
     * los metodos para asignarle el borde
     * @param e
     */
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


    /*
     * Gestion de guardar la partida
     */
    
    /**
     * Metodo guarda la partida con los datos que existen en logica
     */
    public void guardarPartida() {
        //int segundos, int movimientos, int vuelta, ArrayList<String> rutaGuardada, ArrayList<Boolean> cartaBloqueada,String nombre,String avatar
        PartidaGuardada partida = new PartidaGuardada(juego.getContadorSeg(), juego.getContMov(), juego.algunaVisible(), juego.guardarUrlCarta(), juego.guardarBloquearCarta(), nombre, avatar);

        try {
            //Creamos un flujo de salida al disco
            FileOutputStream fileOut = new FileOutputStream(new File(ARCHIVO_PARTIDA_GUARDADA).getAbsolutePath());
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
        JOptionPane.showMessageDialog(null, "The game will be saved.", "Saving...", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Metodo carga la partida y guarda los datos que coge el juego, el resto de
     * datos los recogera en funcion los pida y los necesite
     * @return
     */
    public boolean cargarPartida() {
        partidaGuardada = null;
        if (!new File(ARCHIVO_PARTIDA_GUARDADA).exists()) {
            JOptionPane.showMessageDialog(null, "There is no saved game.\nTry saving a game.", "Saved game not found", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            //Creamos un flujo de entrada desde el disco
            FileInputStream fileIn = new FileInputStream(new File(ARCHIVO_PARTIDA_GUARDADA).getAbsolutePath());
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
        avatar = partidaGuardada.getAvatar();
        nombre = partidaGuardada.getNombre();
        //si se quiere eliminar el fichero cambiar la ELIMINAR_PARTIDA_GUARDADA
        if (ELIMINAR_PARTIDA_GUARDADA) {
            eliminarFichero(ARCHIVO_PARTIDA_GUARDADA);
        }
        partidaCargadaOn = true;
        return true;
    }


    /**
     * Metodo retorna de la partida guardada las cartas que han sido
     * desactivasdas y lo retorna
     * @return int numero de cartas desactivadas
     */
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


    /**
     * Metodo bloquea las cartas de la partida guardada
     */
    public void bloquearCartas() {
        ArrayList<Boolean> cartaBloqueada = partidaGuardada.getCartaBloqueada();
        for (int i = 0; i < cartaBloqueada.size(); i++) {
            if (cartaBloqueada.get(i)) {//si bloquear == true esta desactivada
                juego.bloquearUna(i);
                //juego.girar(i);
            }
        }
    }


    /**
     * Metodo retorna los movimientos de la partida guardada, en el caso de que
     * alguna carta estubiera sin pareja al guardar la partida, la mueve
     * @return movimientos de la partida gusrdada
     */
    public int obtenerMovimientos() {
        //tiene que ser en este orden, primero comprobar si hay carta y moverla 
        //ya que si se hace al reves(en caso de no ser return) , sumaria un movimiento
        //extra, ya que ajustas los movimientos y despues sumas uno al mover la carta
        //ya que en getMovimientos ya cuenta el movimieto de esa carta
        if (partidaGuardada.getVuelta() != -1) {//si no hay ninguna carta dada la vuelta retornamos esto
            juego.girar(partidaGuardada.getVuelta());
        }
        return partidaGuardada.getMovimientos();
    }


    /**
     * Metodo retona el tiempo, en segundos, en el momento que se guardo la
     * partida
     * @return int tiempo en segudos
     */
    public int obtenerTiempo() {
        return partidaGuardada.getSegundos();
    }


    /*
     * Vista Lista
     * Vista Lista
     * Vista Lista
     * Vista Lista
     */
    /**
     * Metodo asigna el controlador de Vlista
     * @param lista Controlador de VLista
     */
    public void asignarContrLista(ContrLista lista) {
        this.lista = lista;
    }


    /**
     * Encargado de controladar las acciones de la vista VLista
     * @param componente en el que se produzco la accion
     */
    public void listaClick(Component componente) {
        if (componente instanceof JButton) {
            JButton bPulsado = (JButton) componente;
            switch (bPulsado.getToolTipText()) {
                case "Go to main":
                    lista.cambiarVista("principal");
                    break;
                default:
                    System.out.println("Elemenot no eseperado en listaClick");
            }
        } else {
            System.out.println("evento no esperado en lista");
        }
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
    public void asignarContrPrincipal(ControladorPrincipal principal) {
        this.principal = principal;
    }


    public void principalClick(String boton, int pulsos) {
        switch (boton.replaceAll(" ", "").toLowerCase()) {
            case "newgame":
                principal.cambiarDeVista("ingresodatos");
                break;
            case "loadgame":
                if (cargarPartida()) {
                    menu.cambiarVista("juegoguardado");
                }
                break;
            case "stats":
                principal.cambiarDeVista("lista");
                break;
            case "aboutus":
                principal.cambiarDeVista("aboutus");
                break;
            case "":
                principal.cambiarDeVista("pascua");
                break;
            default:
                System.out.println("Error en principalClick " + boton);
        }
    }


    /*
     * SONIDO
     * SONIDO
     * SONIDO
     * SONIDO
     * SONIDO
     */
    /**
     * Encargado de Hestionar el sonido, se elije un sonido aleatorio entre 1 y
     * 3 de la accion
     * @param accion
     */
    public void crearSonido(String accion) {
        if (sonido) {//mientras que el sonido este habilitado
            String rutaSonido = RUTA_SONIDO_MAIN + accion + ((int) (Math.random() * 3) + 1) + ".wav";
            Sonido reproducir = new Sonido(rutaSonido);
            reproducir.start();
        }
    }
}
