/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;


import java.io.Serializable;
import javax.swing.ImageIcon;


/**
 *
 * @author Guille
 */
public class Historial implements /*
         * Comparable<Historial>,
         */ Serializable {

    private String nombre, url;
    // private ImageIcon imagen;
    private int tiempo, movimientos;


    public Historial(int movimientos, int tiempo, String url, String nombre) {
        this.nombre = nombre;
        this.url = url;
        // this.imagen = imagen;
        this.tiempo = tiempo;
        this.movimientos = movimientos;
    }


    public String getNombre() {
        return nombre;
    }


    public String leer() {
        return "Movimientos: " + movimientos + " Tiempo: " + tiempo + "Imagen " + url + "Nombre: " + nombre + "";
    }


    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return movimientos + ";" + tiempo + ";" + url + ";" + nombre;//"Movimientos: "+movimientos+" Tiempo: "+tiempo+"Imagen "+url+"Nombre: "+nombre+"";
    }
//    public ImageIcon getImagen() {
//        return imagen;
//    }


    public int getTiempo() {
        return tiempo;
    }


    public int getMovimientos() {
        return movimientos;
    }

//    @Override
//    public int compareTo(Historial e) {
//        if(e.getMovimientos()>movimientos){
//            return -1;
//        }else if(e.getMovimientos()<movimientos){
//            return 0;
//        }else{
//            if(e.getTiempo()>tiempo){
//                return -1;
//            }else if(e.getTiempo()<tiempo){
//                return 0;
//            }else{
//                return 1;
//            }  
//        }    
//    }
}
