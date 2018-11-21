/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guille
 */
public class PartidaGuardada implements Serializable{
    //private static int numeroPartida;
    private int segundos,movimientos,vuelta;//desactivadas;
    private ArrayList<String> rutaGuardada;
    private ArrayList<Boolean> estadoCarta;

    public PartidaGuardada(int segundos, int movimientos, int vuelta, ArrayList<String> rutaGuardada, ArrayList<Boolean> estadoCarta) {
        this.segundos = segundos;
        this.movimientos = movimientos;
        this.vuelta = vuelta;
        this.rutaGuardada = rutaGuardada;
        this.estadoCarta = estadoCarta;
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

    public ArrayList<Boolean> getEstadoCarta() {
        return estadoCarta;
    }

    
    
    
    
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public void setVuelta(int vuelta) {
        this.vuelta = vuelta;
    }

    public void setRutaGuardada(ArrayList<String> rutaGuardada) {
        this.rutaGuardada = rutaGuardada;
    }

    public void setEstadoCarta(ArrayList<Boolean> estadoCarta) {
        this.estadoCarta = estadoCarta;
    }
    
    
}
