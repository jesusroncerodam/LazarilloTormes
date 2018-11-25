package controladores;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import vista.VJuego;
import lazarilloTormes.Logica;


/**
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class ContrJuego extends MouseAdapter implements KeyListener {

    private VJuego vistaJuego;
    private Logica logica;


    /**
     * Construcyor de Controlador del juego se asigna la vista y la logca
     * @param vista  vista a la que se refiere, para poder comunicarse con ella
     * @param logica logica a la que se refiere para comunicarse
     */
    public ContrJuego(VJuego vista, Logica logica) {
        this.vistaJuego = vista;
        this.logica = logica;
    }


    /**
     * Encargado de mandar a la logica la referencia del controlador para poder
     * comunicarse con la vista a través de esta
     */
    public void asignarControlador() {
        this.logica.asignarContrJuego(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //vista.eliminarElementos();
        logica.juegoClick(e.getComponent());
    }


    @Override
    public void keyTyped(KeyEvent e) {
        logica.juegokey(e.getKeyChar());
    }


    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
    }


    /**
     * Comunicacion de logica a vista, manda una accion sobre el contador de
     * tiempo
     * @param accion string con el nombre de la accion
     */
    public void gestionarContador(String accion) {
        vistaJuego.gestionarContador(accion);
    }


    /**
     * Seter, cambia la variable booleana de victoria
     * @param victoria booelan
     */
    public void setVictoria(boolean victoria) {
        vistaJuego.setVictoria(victoria);
    }


    /**
     * Comunicacion de logica a vista, hace animar una imagen , que entre o que
     * salga
     * @param i indice a girar
     */
    public void girar(int i) {
        vistaJuego.girar(i);
    }


    /**
     * Comunicacion de logica a vista, retorna si hay alguna carta que no este
     * bloqueda
     * y que este visible
     * @return indice de la carta, -1 en caso de que no tenga ninguna visible
     */
    public int algunaVisible() {
        return vistaJuego.algunaVisible();
    }


    /**
     * Comunicacion de logica a vista, compara si las imagenes son las mismas
     * @param i indice de la carta a comparar
     * @param j indice de la carta a comparar
     * @return boolean true en caso de que sean la misma carta
     */
    public boolean mismaImagen(int i, int j) {
        return vistaJuego.mismaImagen(i, j);
    }


    /**
     * Comunicacion de logica a vista, bloquea la imagen para que no se poducan
     * mas animaciones ni cuente esa imagen para hacer click en ella
     * @param i indice de la carta a bloquear
     * @param j indice de la carta a bloquear
     */
    public void bloquearImagenes(int i, int j) {
        vistaJuego.bloquearImagenes(i, j);
    }


    /**
     * Comunicacion de logica a vista, suma uno al contador de movimientos
     * y lo muestra
     */
    public void movimiento() {
        vistaJuego.movimiento();
    }


    /**
     * Comunicacion de logica a vista, retorna si el juego a terminado
     * @return boolean false si es el fin del juego
     */
    public boolean isFin() {
        return vistaJuego.isFin();
    }


    /**
     * Comunicacion de logica a vista, cambia el estado de un boton, lo habilita
     * o desabilita
     * @param boton  String nombre del boton a cambiar el estado
     * @param estado boolean, estado a cambiar
     */
    public void cambiarEstadoBoton(String boton, boolean estado) {
        vistaJuego.cambiarEstadoBoton(boton, estado);
    }


    /**
     * Comunicacion de logica a vista, retorna los segundos actualess
     * @return int seguntos
     */
    public int getContadorSeg() {
        return vistaJuego.getContadorSeg();
    }


    /**
     * Comunicacion de logica a vista, retorna los movimientos actuales
     * @return int movimiento
     */
    public int getContMov() {
        return vistaJuego.getContMov();
    }


    /**
     * Comuncicacion vista a logica, obtiene las rutas de las imagenes a cargar
     * en el juego
     * @return Array de String, con las rutas
     */
    public String[] obtenerRutas() {
        return logica.obtenerRutasImg();
    }


    /**
     * Retona las rutas de las cartas, en orden mostradas, para poderlas
     * almaenarlas en guardar partida.
     * @return ArrayList de string
     */
    public ArrayList<String> guardarUrlCarta() {
        return vistaJuego.guardarUrlCarta();
    }


    /**
     * Retona las cartas bloqueadas,es decir las cartas que igualaron,
     * en orden mostrado, para poderlas almacenarlas
     * en guardar partida.
     * @return arraylist booleano de cartas bloqueadas
     */
    public ArrayList<Boolean> guardarBloquearCarta() {
        return vistaJuego.guardarBloquearCarta();
    }


    /**
     * Metodo llama a la logica para que bloquee las cartas que fueron guardas,
     * parte de cargar partida
     */
    public void bloquearCartas() {
        logica.bloquearCartas();
    }


    /**
     * Metodo encargado de retornar el tiempo, en segundos parte de cargar
     * partida
     * @return int, segundos
     */
    public int obtenerTiempo() {
        return logica.obtenerTiempo();
    }


    /**
     * Metodo encargado de retornar los movimientos ejecutados en la partida
     * guardada,
     * en el caso de que alguna carta estuviese volteada pero sin pareja, se
     * cargaria esta
     * @return int, cantidad de movimientos
     */
    public int obtenerMovimientos() {
        return logica.obtenerMovimientos();
    }


    /**
     * Metodo encargado de ajustar las cartdas desactivasas , parte de cargar
     * partida
     * @return cartas desactivadas
     */
    public int obtenerGuardadDesact() {
        return logica.obtenerGuardadDesact();
    }


    /**
     * Metodo encargado de bloquear las cartas que se guardaron
     * @param indice indice de la carta a bloquear
     */
    public void bloquearUna(int indice) {
        vistaJuego.bloquearUna(indice);
    }


    /**
     * Metodo se le llama desde la logica para cambiar de vista
     * @param vistaDestino String que corresponde al nombre de la vista de
     *                     destino
     */
    public void cambiarVista(String vistaDestino) {
        vistaJuego.cambiarVista(vistaDestino);
    }
}
