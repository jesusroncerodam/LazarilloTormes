/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package trabajodi;

import controladores.ContrJuego;
import java.awt.Component;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;


/**

 @author Guille
 */
public class Logica {
    private ContrJuego juego;
    private boolean primeraJuego;
    public Logica() {
    }
    
    public void asignarContrJuego(ContrJuego juego){
        this.juego=juego;
        primeraJuego=true;
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
        if(primeraJuego){
            juego.gestionarContador("empezar");
            primeraJuego=false;
        }
        if(componente instanceof JButton){
            String accion=((JButton) componente).getActionCommand();
            switch (accion) {
                case "playPause":
                case "continuar":
                    juego.gestionarContador(accion);
                    break;
                case "guardar":
                    
                    break;
                default:
                    System.out.println(accion);
                    //llamar a la de los menuses
            }
           // switch
            
            
            
        }else if(componente instanceof JLabel){//defaultIcon=src/img/cartas/vuelta.png
            JLabel a=(JLabel) componente;
            int vuelta;
            int cartaAct=Integer.parseInt(a.getName());
            
            juego.movimiento();//se realiza 1 movimiento, sumamos 1 al contador
            //comprobar si hay alguna mas del reves
            vuelta=juego.algunaVisible();
            
            //girar carta
            juego.girar(cartaAct);
            

            if(vuelta!=-1){//si es dif de  -1 hay 2 visibles
                if(juego.mismaImagen(vuelta, cartaAct)){//si las cartas que hay son =
                    juego.bloquearImagenes(vuelta, cartaAct);
                    if(fiasfj)
                }else{
                    Timer timer=new java.util.Timer();
                    TimerTask tarea =new TimerTask() {
                    @Override
                    public void run() {
                        juego.girar(cartaAct);
                        juego.girar(vuelta);
                    }};
                    timer.schedule(tarea, 4000);
                }
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


    public synchronized void dormir(int tiempo){
        try {
            Thread.sleep(tiempo*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
