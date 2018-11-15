/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package trabajodi;

import controladores.ContrJuego;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;


/**

 @author Guille
 */
public class Logica {
    private ContrJuego juego;
    
    public Logica() {
    }
    
    public void asignarContrJuego(ContrJuego juego){
        this.juego=juego;
    }
    
    public void Menus(String accion){
        
    }
    /*
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     // CONTROLADOR VISTA JUEGO
     */
    public void juegoClick(Component componente) {
        if(componente instanceof JButton){
            String accion=((JButton) componente).getActionCommand();
            switch (accion) {
                case "playPause":
                    
                    break;
                case "guardar":
                    
                    break;
                case "continuar":
                    
                    break;
                default:
                    System.out.println(accion);
                    //llamar a la de los menuses
            }
           // switch
            System.out.println( );
            
            
            
        }else if(componente instanceof JLabel){//defaultIcon=src/img/cartas/vuelta.png
            JLabel a=(JLabel) componente;
            int vuelta;
            System.out.println(a.getName());
            System.out.println("label");
            //comprobar si hay alguna mas del reves
            vuelta=juego.algunaVisible();
            
            //girar carta
            juego.girar(Integer.parseInt(a.getName()));
            
            if(vuelta==-1){//si es = a -1 no hay ninguna visible
                
            }
        }
    }
    public void juegokey() {

    }

    /*
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     // CONTROLADOR VISTA CARGA
     */
    public void nombre_metodo() {

    }


    /*
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     // CONTROLADOR VISTA DIALOGMOD
     */
    public void metodo1() {

    }


    /*
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     // CONTROLADOR VISTA INGRESO
     */
    public void metodo2() {

    }


    
}
