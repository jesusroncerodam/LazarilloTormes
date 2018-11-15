/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package trabajodi;

import controladores.ContrJuego;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private final String FICHERO="estadisticas.txt";
    
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
         gestionFichero();
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
                    
                case "continuar"://guardar los datos
                    guardarDatos();
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
                        timer.schedule(tarea, 1000);
                    }
                }
            }else{
                System.out.println("jfbsj");
            }
        }
    }
    private void guardarDatos(){
        //nombre//ya lo tengo 
        //imagen //ya lo tengo
        //movimientos
        juego.getContMov();
        //tiempo
        juego.getContadorSeg();
        
    }
    
    public void juegokey() {
        
    }
    
    private void gestionFichero(){
        crearFichero();
    }
    //        fichero.delete();

    private void crearFichero(){
        try{
            File archivo = new File(FICHERO);
            System.out.println(archivo.getAbsoluteFile());
            if(!archivo.exists()){
                FileWriter escritor=new FileWriter(archivo,true);
                escritor.write("Directorio de almacenamiento de estadistica");
                escritor.close();
            }
        }catch(Exception e){
        System.out.println("Error al escribir");//Si existe un problema al escribir cae aqui
        }
    }
    
    public void leer() {
    //Creamos un String que va a contener todo el texto del archivo
            String texto = "";

            try {
    //Creamos un archivo FileReader que obtiene lo que tenga el archivo
                FileReader lector = new FileReader(FICHERO);

    //El contenido de lector se guarda en un BufferedReader
                BufferedReader contenido = new BufferedReader(lector);

    //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
                while ((texto = contenido.readLine()) != null) {
                    System.out.println(texto);
                }
            } //Si se causa un error al leer cae aqui
            catch (Exception e) {
                System.out.println("Error al leer");
            }
        }
    public void test(){
        
    }
    public int Comparator(Historial hist1,Historial hist2){
        if(hist1.getMovimientos()==hist2.getMovimientos()){
            return hist1.getTiempo()-hist2.getTiempo();
        }
        return hist2.getMovimientos()-hist1.getMovimientos();
    }   
/*
    }
    /*
    help clss
    tein buf
    autonum orden
    eliminar array


    instance ordenrar
    arrayls interfaz
    abst addobj








    busfic sigfic
    mosfic auxfic
    insoacfic actfic
    visfic arrdecl
    arrordenum arrbus
    arrbus arrayud
    arrorclas arrorclas
    arrcodrep arrcodrep

    try*/
            

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
