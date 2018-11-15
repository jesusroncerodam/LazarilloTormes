/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

import javax.swing.ImageIcon;

/**
 *
 * @author Guille
 */
public class Historial implements Comparable<Historial>{
    private String nombre;
//    private ImageIcon imagen;
    private int tiempo,movimientos;

    public Historial(String nombre, /*ImageIcon imagen,*/ int tiempo, int movimientos) {
        this.nombre = nombre;
       // this.imagen = imagen;
        this.tiempo = tiempo;
        this.movimientos = movimientos;
    }

    public String getNombre() {
        return nombre;
    }
    public String toString(){
        return nombre+" "+movimientos+" "+tiempo;
    }
//
//    public ImageIcon getImagen() {
//        return imagen;
//    }

    public int getTiempo() {
        return tiempo;
    }

    public int getMovimientos() {
        return movimientos;
    }

    @Override
    public int compareTo(Historial e) {
        if(e.getMovimientos()>movimientos){
            return -1;
        }else if(e.getMovimientos()<movimientos){
            return 0;
        }else{
            if(e.getTiempo()>tiempo){
                return -1;
            }else if(e.getTiempo()<tiempo){
                return 0;
            }else{
                return 1;
            }  
        }    
    }
   
}
