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
    private boolean primeraJuego,animacionC;
    private Timer timer;
    private  int vuelta,cartaAct;
    
    public Logica() {
    }
    
    public void asignarContrJuego(ContrJuego juego){
        this.juego=juego;
        primeraJuego=true;
        animacionC=false;
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
           

    public synchronized void juegoClick(Component componente) {
        if(primeraJuego){//si se ejecuta laguna accion
            juego.gestionarContador("empezar");
            primeraJuego=false;
        }
        
        if(componente instanceof JButton){//si es un boton
            String accion=((JButton) componente).getActionCommand();
            switch (accion) {
                case "playPause":
                    System.out.println(accion);
                    juego.gestionarContador(accion);
                    break; 
                    
                case "continuar":
                    //guardar los datos
                    break;
                    
                case "guardar":
                    System.out.println("dkbfjsdab");
                    break;
                    
                default:
                    System.out.println(accion);
                    //llamar a la de los menuses
            }
            
        }else if(componente instanceof JLabel){//si es un JLabel
            if( !animacionC ){
                JLabel a=(JLabel) componente;
                vuelta=juego.algunaVisible();
                cartaAct=Integer.parseInt(a.getName());
                if(vuelta==cartaAct){//comprobamos que no tenga hecho click en la misma imagen
                    vuelta=-1;
                }
                juego.movimiento();//se realiza 1 movimiento, sumamos 1 al contador
                //comprobar si hay alguna mas del reves
                //vuelta=juego.algunaVisible();

                //girar carta
                juego.girar(cartaAct);

                    System.out.println(""+cartaAct);
                    System.out.print(" "+vuelta);
                if(vuelta!=-1){//si es dif de  -1 hay 2 visibles
                    if(juego.mismaImagen(vuelta, cartaAct)){//si las cartas que hay son =
                        System.out.println("bloqueamos "+vuelta+" y "+cartaAct);
                        juego.bloquearImagenes(vuelta, cartaAct);
                        //=-1;                    vuelta=-1;

                        if(!juego.isFin()){//si es el fin
                            juego.gestionarContador("pausa");
                            //cambiamos los estados de los botones 
                            juego.cambiarEstadoBoton("guardar", false);
                            juego.cambiarEstadoBoton("playPause", false);
                            juego.cambiarEstadoBoton("continuar", true);
                        }
                    }else{
                        animacionC=true;
                        timer=new java.util.Timer();
                        TimerTask tarea =new TimerTask() {
                        @Override
                        public void run() {
                            juego.girar(cartaAct);
                            juego.girar(vuelta);
                            timer=null;
                            animacionC=false;
                        }};
                        timer.schedule(tarea, 2000);
                    }
                }
            }else{
                System.out.println("jfbsj");
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
