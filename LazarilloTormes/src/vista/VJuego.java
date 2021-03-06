package vista;


import controladores.ContrJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import lazarilloTormes.Logica;
import lazarilloTormes.Vista;


/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class VJuego extends JPanel {

    private Vista vistaMain;
    private Font fuente = new Font("Agency FB", Font.BOLD, 40);
    private ContrJuego controlador;
    private ArrayList<Carta> carta;
    private int contadorSeg, contMov, desactivadas;
    private Timer tReloj;
    private JLabel lMovimientos, lReloj;
    private JButton bPausaPlay, bGuardar, bContinuar;
    private GridBagConstraints constrain;
    private boolean victoria;
    private final String RUTA_PLAYPAUSE = "/img/playPause.png",
            RUTA_RELOJ = "/img/relojWh.png",
            RUTA_FLECHA = "/img/flechaRect.png",
            RUTA_GUARDAR = "/img/save.png",
            RUTA_FONDO = "/img/fondoJuego.gif",
            RUTA_VICTORIA = "/img/victoria/1.gif";
    private final int HGAP = 20,
            VGAP = 5;


    /**
     * Constructor, solo se asigna la logica y vista, el resto se genera 
     * llamando al metodo generar
     * @param logica logica 
     * @param vista vista a la que refiere(padre)
     */
    public VJuego(Logica logica, Vista vista) {
        this.vistaMain = vista;
        controlador = new ContrJuego(this, logica);
    }


    /**
     * Encargado de generar un juego nuevo, inicialiA todas las variables y
     * controladores
     *
     */
    public void generar() {
        controlador.asignarControlador();//asignamos el controlador a la logica
        desactivadas = 0;
        victoria = false;
        this.setOpaque(true);
        constrain = new GridBagConstraints();
        constrain.weighty = 0.5;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());
        //asignamos los labels de tiempo y movimientos
        asignarLabels();
        //generamos los botones
        continuar();
        playPause();
        guardar();
        //generamos las cartas
        generarCartas(true);//pata que se deshordenen


        this.addKeyListener(controlador);
        FocusManager.getCurrentManager().focusNextComponent(this);
        this.requestFocus();
        //System.out.println(FocusManager.getCurrentManager().getFocusOwner());
    }


    /**
     * Asignamos los labels, tanto el de movimientos como el temporizador
     */
    private void asignarLabels() {
        //contador de movimientos
        contMov = 0;
        lMovimientos = new JLabel("Movimientos: " + contMov);
        lMovimientos.setFont(fuente);
        lMovimientos.setForeground(new Color(74, 110, 242));///////////////////////////////////////////////////////////////////////////////////////////////////////////CAMBIAR COLOR, PONER VARIABLE
        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.fill = GridBagConstraints.BOTH;
        this.add(lMovimientos, constrain);

        //contador segundos
        contadorSeg = 0;
        ImageIcon imgReloj = new ImageIcon(this.getClass().getResource(RUTA_RELOJ));
        lReloj = new JLabel("00:" + contadorSeg, imgReloj, JLabel.CENTER);

        lReloj.setHorizontalTextPosition(SwingConstants.CENTER);
        lReloj.setVerticalTextPosition(SwingConstants.CENTER);
        // lReloj.setIconTextGap((int) (-imgReloj.getIconWidth() / 1.6));//si no hacemos esto, el texto saldría a la derecha de la imagen, no encima
        lReloj.setFont(fuente);
        lReloj.setForeground(new Color(74, 110, 242));///////////////////////////////////////////////////////////////////////////////////////////////////////////CAMBIAR COLOR, PONER VARIABLE
        constrain.gridx = 2;
        constrain.gridy = 0;
        this.add(lReloj, constrain);
        constrain.fill = GridBagConstraints.NONE;
    }


    /**
     * Encargado de generar y asignar el JButon de PlayYpause
     */
    private void playPause() {
        bPausaPlay = new JButton(new ImageIcon(this.getClass().getResource(RUTA_PLAYPAUSE)));
        bPausaPlay.setContentAreaFilled(false);
        bPausaPlay.setBorder(null);
        constrain.gridx = 0;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bPausaPlay, constrain);
        
        bPausaPlay.setActionCommand("playPause");
        bPausaPlay.addKeyListener(controlador);
        bPausaPlay.addMouseListener(controlador);
        bPausaPlay.requestFocus();
    }


    /**
     * Encargado de generar y asignar el JButon de continuar
     */
    private void continuar() {
        bContinuar = new JButton(new ImageIcon(this.getClass().getResource(RUTA_FLECHA)));
        bContinuar.setContentAreaFilled(false);
        bContinuar.setBorder(null);
        constrain.gridx = 2;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bContinuar, constrain);
        cambiarEstadoBoton("continuar", false);
        bContinuar.setActionCommand("continuar");
        bContinuar.addKeyListener(controlador);
        bContinuar.addMouseListener(controlador);
    }


    /**
     * Encargado de generar y asignar el JButon de guardar
     */
    private void guardar() {
        bGuardar = new JButton(new ImageIcon(this.getClass().getResource(RUTA_GUARDAR)));
        bGuardar.setContentAreaFilled(false);
        bGuardar.setBorder(null);
        bGuardar.setActionCommand("guardar");
        constrain.gridx = 1;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bGuardar, constrain);

        bGuardar.addKeyListener(controlador);
        bGuardar.addMouseListener(controlador);
    }


    /**
     * Creamos, asignamos y mostramos las images
     * @param rutas Array de string que cotiene las rutas de las imagenes
     */
    private void generarCartas(boolean deshordenar) {
        String[] rutas = obtenerRutas();
        carta = new ArrayList();

        if (deshordenar) {
            for (int i = 0; i < rutas.length; i++) { //asignamos la misma ruta a 2 carta
                carta.add(new Carta(rutas[i]));
                carta.add(new Carta(rutas[i]));
            }
        } else {
            for (int i = 0; i < rutas.length; i++) {
                carta.add(new Carta(rutas[i]));//cargamos las cartas, de la partida guardada
            }
        }

        JPanel cartas = new JPanel();//creamos el panel donde estarán las cartas
        cartas.setOpaque(false);//fondo transparente para que se vea el fondo

        int cuadrado = (int) Math.sqrt(carta.size());//la raiz da lugar a unas filas respectivas 
        cartas.setLayout(new GridLayout(cuadrado, cuadrado, HGAP, VGAP));//asignamos un layout a las cartas

        if (deshordenar) {
            Collections.shuffle(carta);//desordenamos las cartas asi estan colocadas de manera aleatoria
        }
        for (int i = 0; i < carta.size(); i++) {//asignamos el indice al nombre de las cartas
            carta.get(i).setName("" + i);
        }

        for (int i = 0; i < carta.size(); i++) {//anadimos todas las cartas y les ponemos escuchador
            cartas.add(carta.get(i));
            carta.get(i).addMouseListener(controlador);
        }
        constrain.gridx = 0; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1
        constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
        constrain.gridheight = 1; // El área de texto ocupa 1 filas.
        constrain.fill = GridBagConstraints.NONE; //para que no se expanda
        constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(cartas, constrain);
    }


    /**
     * Encargado de iniciar el contador
     */
    private void empezarReloj() {
        tReloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contadorSeg++;
                ponerTiempo();
                repaint();
            }
        });
    }


    /**
     * Ajusta el tiempo en minutos, y segundos
     */
    private void ponerTiempo() {
        int minutos = contadorSeg / 60;
        int seg = contadorSeg % 60;
        String tiempo = "00:" + contadorSeg;
        if (minutos < 10) {
            tiempo = "0" + minutos + ":";
        } else {
            tiempo = minutos + ":";
        }
        if (seg < 10) {
            tiempo += "0" + seg;
        } else {
            tiempo += seg;
        }
        lReloj.setText(tiempo);
    }


    /**
     * Devuelve las rutas de las imagenes
     * @return
     */
    private String[] obtenerRutas() {
        return controlador.obtenerRutas();
    }


    /**
     * Pintamos el Fondo
     * @param g paint
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage((new ImageIcon(this.getClass().getResource(RUTA_FONDO))).getImage(), 0, 0, getWidth(), getHeight(), this);//this ya que es un gif y si no ponemos this, no se animaria
        if (victoria) {
            g.drawImage((new ImageIcon(this.getClass().getResource(RUTA_VICTORIA))).getImage(), 0, 0, getWidth(), getHeight(), this);//this ya que es un gif y si no ponemos this, no se animaria
        }

    }


    /*
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     * VIENE DE CONTROLADOR
     */
    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
        repaint();
    }


    /**
     * Empieza crea o pausa el contador del timepo
     * @param accion String a realizar
     */
    public void gestionarContador(String accion) {
        if (tReloj == null) {
            empezarReloj();
        }
        switch (accion.toLowerCase()) {
            case "empezar":
            case "play":
            case "reanudar":
                tReloj.setRepeats(true);
                tReloj.start();
                break;

            case "pausa":
            case "pause":
                tReloj.stop();
                break;

            case "playpause":
                if (tReloj.isRunning()) {
                    tReloj.stop();
                } else {
                    tReloj.start();
                }
                tReloj.setRepeats(true);
                break;

            default:
                System.out.println("error gestionar contador" + accion.toLowerCase());
        }
    }


    /**
     * Anima la imagen la gira mostrando el fondo
     * @param i int, indice del arraylist de las cartas
     */
    public void girar(int i) {
        carta.get(i).animar();
    }


    /**
     * Retorna el entero que esta visible y sin bloquear, es decir el que este
     * sin
     * su pareja ya asignada, -1 si no encuentra a nadie
     * @return int valor de carta viible sin bloquear, -1 si no lo encuentra
     */
    public int algunaVisible() {
        for (int i = 0; i < carta.size(); i++) {
            if (carta.get(i).isSale()) {
                return i;
            }
        }
        return -1;
    }


    /**
     * retorna si al imagen que ha hecho click apunta la imagen al la misma
     * imagrn (si tiene la misma ruta)
     * @param i indice de 1 carta a comparar
     * @param j indice de 2 carta a comparar
     * @return boolean, si son las mismsa imagen
     */
    public boolean mismaImagen(int i, int j) {
        return (carta.get(i).getUrl().equals(carta.get(j).getUrl()));
    }


    /**
     * bloquea pareja de imagenes para que no se puedan volver a girar y asi no
     * se muestren en al busar umagenes, tambien suma 2 a las cartas
     * descativadas
     * @param i indice del array de la carta a bloquear
     * @param j indice del array de la carta a bloquear
     */
    public void bloquearImagenes(int i, int j) {
        carta.get(i).bloquear();
        carta.get(j).bloquear();
        desactivadas += 2;
    }


    /**
     * Añade un movimiento al contador de movimientos
     */
    public void movimiento() {
        contMov++;
        lMovimientos.setText("Movements: " + contMov);
    }


    /**
     * retornas la partida finalizo
     * @return booleano, true si aun no termino
     */
    public boolean isFin() {
        return carta.size() > desactivadas;
    }


    /**
     * Cambia el estado del boton, activado o desactivado
     *
     * @param boton  String, texto, corresponde al boton
     * @param estado booleano, pone el estado de este boton
     */
    public void cambiarEstadoBoton(String boton, boolean estado) {
        switch (boton) {
            case "continuar":
                bContinuar.setEnabled(estado);
                break;
            case "guardar":
                bGuardar.setEnabled(estado);
                break;
            case "playPause":
                bPausaPlay.setEnabled(estado);
                break;
            default:
                System.out.println("error cambiarEstadoBoton" + boton);
        }
    }


    /**
     * Retorna los segundos actuales, en el momento que se le llama
     * @return int segundos
     */
    public int getContadorSeg() {
        return contadorSeg;
    }


    /**
     * Retorna los movimientos actuales, en el momento que se le llama
     * @return int movimientos
     */
    public int getContMov() {
        return contMov;
    }


    /**
     * Retorna las rutas de las imagenes en orden de colocacion
     * @return ArrayList de String
     */
    public ArrayList<String> guardarUrlCarta() {
        ArrayList<String> rutaGuardada = new ArrayList<String>();
        for (int i = 0; i < carta.size(); i++) {
            rutaGuardada.add(carta.get(i).getUrl());
            //System.out.println(i+" ->"+carta.get(i).getUrl());
        }
        return rutaGuardada;
    }


    /**
     * Retorna las cartas que han sido bloqueadas
     * @return ArrayList de Boolean
     */
    public ArrayList<Boolean> guardarBloquearCarta() {
        ArrayList<Boolean> estadoCarta = new ArrayList<Boolean>();
        for (int i = 0; i < carta.size(); i++) {
            estadoCarta.add(carta.get(i).getBloquear());
            //System.out.println(i+" ->"+carta.get(i).getUrl());
        }
        return estadoCarta;
    }


    /**
     * Recive un arrayList booleano y bloquea los indices que sean true
     * @param bloqueadas arrayList booleano
     */
    public void cargarBloquear(ArrayList<Boolean> bloqueadas) {
        for (int i = 0; i < carta.size(); i++) {
            if (bloqueadas.get(i)) { //si bloquear es true lo bloqueamos
                carta.get(i).bloquear();
            }
        }
    }


    /**
     * Metodo encargado de generar las partida Guardada, llama a todoss
     * los metodos pero asignandoles valores para la partida guardada
     */
    public void generarGuardada() {
        controlador.asignarControlador();//se tiene que volver a generar
        victoria = false;

        this.setOpaque(true);
        this.setFocusable(true);
//        this.requestFocus();
        this.addKeyListener(controlador);

        constrain = new GridBagConstraints();
        constrain.weighty = 0.5;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());

        //asignamos los labels de tiempo y movimientos
        asignarLabels();

        //generamos los botones
        playPause();
        guardar();
        continuar();

        //generamos las cartas
        generarCartas(false);
        controlador.bloquearCartas();
        //se tiene que hacer en este orden
        contMov = controlador.obtenerMovimientos();
        lMovimientos.setText("Movements: " + contMov);

        desactivadas = controlador.obtenerGuardadDesact();

        contadorSeg = controlador.obtenerTiempo();

        ponerTiempo();
    }


    /**
     * Metodo llamado desde controlador, que bloqeua una carta, usado en logica
     * para asignar las cartas bloqueadas
     * @param indice int indice del array a bloquear
     */
    public void bloquearUna(int indice) {
        carta.get(indice).bloquear();
    }


    /**
     * Es el metodo que se encarga de vincular la vista principal con el resto,
     * es comun en todas las vistas
     *
     * le manda un string que viene de la logica e indica a que vista nos
     * estamos moviendo
     * @param vista String, es el nombre de la vista a la que vamos a cambiar
     */
    public void cambiarVista(String vista) {
        vistaMain.cambiarVista(vista);
    }
}
