/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

/**
 *
 * @author Guille
 */
public class Historial implements Comparable<Historial>{
    private String nombre, url;
    private int tiempo, movimientos;

    /**
     * Constructor de historial, implementa Comparable para así ordenar primero
     * por tiempo y despues  por movimientos
     * @param movimientos int, cantidad de movimientos
     * @param tiempo int, cantidad de segundos
     * @param url string, ruta de la imagen del avatar
     * @param nombre string, nombtre o nickname del jugador
     */
    public Historial(int movimientos, int tiempo, String url, String nombre) {
        this.nombre = nombre;
        this.url = url;
        this.tiempo = tiempo;
        this.movimientos = movimientos;
    }


    public String getNombre() {
        return nombre;
    }

    public String getUrl() {
        return url;
    }

    public int getTiempo() {
        return tiempo;
    }


    public int getMovimientos() {
        return movimientos;
    }
    
    /**
     * Sobreescrito, retorna valores intercalados por ; 
     * la cadena seria-> movimientos;tiempo;url;nombre
     * @return String de valores
     */
    @Override
    public String toString() {
        return movimientos + ";" + tiempo + ";" + url + ";" + nombre;//
    }

    /**
     * Ordenamos primero por tiempo y en caso de empate por movimientos
     * @param e Historial, a comparar
     * @return int 1 0 o -1 en funcion del caso
     */
    @Override
    public int compareTo(Historial e) {
        if(e.getTiempo()>tiempo){
            return -1;
        }else if(e.getTiempo()<tiempo){
            return 0;
        }else{
            if(e.getMovimientos()>movimientos){
                return -1;
            }else if(e.getMovimientos()<movimientos){
                return 0;
            }else{
                return 1;
            }
        }
    }
}
