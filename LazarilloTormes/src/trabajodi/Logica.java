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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
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
    private final String FICHERO="estadisticas.txt",PRIMERA_LINEA="Directorio de almacenamiento de estadistica\n";
    
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
        //gestionFichero();
        //test();
        //mostrarFichero();
        //sdg();
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
    
    private void gestionFichero(Historial historialNuevo){
        ArrayList<Historial> historial;
        //nos aseguramos que existe un fichero
        crearFichero(true);
        //añadimos el contenido del fichero a un array
        historial= pasarFicheroAArray();
        //añadimos los nuevos valores 
        historial.add(historialNuevo);
        //ordenamos el arraylist
        Collections.sort(historial);
        //lo escribimos en el fichero
        pasarAFichero(historial);
    }
    //        fichero.delete();

    private void crearFichero(boolean mantenerFichero){
        try{
            File archivo = new File(FICHERO);
            System.out.println(archivo.getAbsoluteFile());
            if(!archivo.exists()){
                FileWriter escritor=new FileWriter(archivo,mantenerFichero);//true no sobrescribe
                escritor.write(PRIMERA_LINEA);
                escritor.close();
            }
        }catch(Exception e){
            System.out.println("Error al escribir");//Si existe un problema al escribir cae aqui
        }
    }
    
    public void mostrarFichero() {
    //Creamos un String que va a contener todo el texto del archivo
            String texto = "";
            System.out.println("ficheroooooo");
            System.out.println("================================");
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
        ArrayList historial=new ArrayList<Historial>();
        //String nombre, ImageIcon imagen, int tiempo, int movimientos
        historial.add(new Historial( 20, 10,"url","em"));
        historial.add(new Historial( 50, 15,"url","am"));
        historial.add(new Historial( 20, 15,"url","om"));
        historial.add(new Historial( 2, 10,"url","ma"));
        historial.add(new Historial(50, 18,"url","me"));
        pasarAFichero(historial);
        /*
        for (Object object : historial) {
            System.out.println(object.toString()+"");
        }
        Collections.sort(historial);
        System.out.println("----");
        for (Object object : historial) {
            System.out.println(object.toString()+"");
        }*/
    }
    
    public ArrayList<Historial> pasarFicheroAArray(){//Retorna el contenido del fichero en el array
        String linea;
        String[] lineas;
        ArrayList<Historial> historial=new ArrayList();
        try {
            FileReader f = new FileReader(FICHERO);
            BufferedReader b = new BufferedReader(f);
            b.readLine();//ignoramos la 1º linea
            while((linea = b.readLine())!=null) {
                lineas=linea.split(";");//movimientos;tiempo;imagen;nombre
                historial.add(new Historial(Integer.parseInt(lineas[0]), Integer.parseInt(lineas[1]), lineas[2], lineas[3]));
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return historial;
    }
    
    public void pasarAFichero(ArrayList<Historial> historial){//coge el arrayList y lo escribe en el fichero
        try {
            FileWriter fichero = new FileWriter(FICHERO,false);
            fichero.write(PRIMERA_LINEA);
            for (Historial historiales : historial) {
                fichero.write(historiales.toString() + "\n");
            }
            fichero.close();

        } catch (Exception ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        }
        
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
