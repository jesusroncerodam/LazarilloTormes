package lazarilloTormes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class PartidaGuardada implements Serializable{
    //private static int numeroPartida;
    private int segundos,movimientos,vuelta;//desactivadas;
    private ArrayList<String> rutaGuardada;
    private ArrayList<Boolean> cartaBloqueada;
    private String nombre,avatar;

    /**
     * Constructor se le asignan todos los elementos a guardar estos han de ser 
     * serializables
     * @param segundos int segundos
     * @param movimientos int cantidad de movimientos
     * @param vuelta int, carta que se dio la vuelta
     * @param rutaGuardada ArrayList de String, corresponde a las cartas qu
     * @param cartaBloqueada ArrayList de Boolean, carga 
     * @param nombre String, nombre o nickname del jugador
     * @param avatar String, ruta de la imagen del jugador
     */
    public PartidaGuardada(int segundos, int movimientos, int vuelta, ArrayList<String> rutaGuardada, ArrayList<Boolean> cartaBloqueada,String nombre,String avatar) {
        this.segundos = segundos;
        this.movimientos = movimientos;
        this.vuelta = vuelta;
        this.rutaGuardada = rutaGuardada;
        this.cartaBloqueada = cartaBloqueada;
        this.nombre=nombre;
        this.avatar=avatar;
    }
    
    public int getSegundos() {
        return segundos;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public int getVuelta() {
        return vuelta;
    }

    public ArrayList<String> getRutaGuardada() {
        return rutaGuardada;
    }

    public ArrayList<Boolean> getCartaBloqueada() {
        return cartaBloqueada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAvatar() {
        return avatar;
    }
    
    
}
