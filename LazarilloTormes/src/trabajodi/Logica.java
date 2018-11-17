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
    
    /**
     * asinamos el controlador de Juego y incicializamos valores a un juego por defecto
     * @param juego ContrJuego encargado de la comunicacion con el juego
     */
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
           
    /**
     * Encargado de gestionar las acciones de de mouseListener del controlador 
     * del juego, aqui se gestionaran toda la logica
     * @param componente 
     */
    public synchronized void juegoClick(Component componente) {
        //si se ejecuta laguna accion  comienza el contador
        if(primeraJuego){
            juego.gestionarContador("empezar");
            primeraJuego=false;//ya no es la 1 accion del juego
        }
        
        if(componente instanceof JButton){//si es un boton
            String accion=((JButton) componente).getActionCommand();//guardamos la accion del boton
            switch (accion) {
                case "playPause":
                    juego.gestionarContador(accion);
                    break; 
                    
                case "continuar"://guardar los datos
                    guardarDatos();
                    break;
                    
                case "guardar":
                    System.out.println("en creaccion");
                    break;
                    
                default:
                    System.out.println("error juegoClick opcion no esperada: "+accion);
            }
            
        }else if(componente instanceof JLabel){//si es un JLabel, 
            if(!animacionC){//mientras que no tengamos ninguna animacion en progreso
                JLabel jlComponente=(JLabel) componente;
                
                vuelta=juego.algunaVisible();//antes de mover la carta actual miramos si hay alguna carta visible
                cartaAct=Integer.parseInt(jlComponente.getName());//opbtenemos el indice del array de la carta que ss hizo click
                
                if(vuelta==cartaAct){//comprobamos que no tenga hecho click en la misma imagen 2 veces
                    vuelta=-1;//si es asi la carta que se dio la vuelta no existe, para la logica
                }
                juego.movimiento();//se realiza 1 movimiento, sumamos 1 al contador

                juego.girar(cartaAct);//gira mos la carta que se hizo click
                
                if(vuelta!=-1){//si es diferente de  -1 hay 2 visibles
                    if(juego.mismaImagen(vuelta, cartaAct)){//si las cartas que hay son iguales
                        
                        juego.bloquearImagenes(vuelta, cartaAct);//bloqueamos la imagen, para que no se puedan mover mas

                        if(!juego.isFin()){//si es el fin del juego(Si a termiando)
                            juego.gestionarContador("pausa");
                            //cambiamos los estados de los botones 
                            juego.cambiarEstadoBoton("guardar", false);
                            juego.cambiarEstadoBoton("playPause", false);
                            juego.cambiarEstadoBoton("continuar", true);
                        }
                        
                    }else{//si las cartas que tenemos no son iguales las giramos
                        animacionC=true;//ponermos que existe una animacion en progreso
                        timer=new java.util.Timer();//cremos el timer para crear una animacion
                        TimerTask tarea =new TimerTask() {//creamos un timerTask, que se ejecutara en x segundos
                            @Override
                            public void run() {
                                juego.girar(cartaAct);//girampos la carta actual 
                                juego.girar(vuelta);//girampos la que esta visble
                                timer=null;//eliminamos el timer
                                animacionC=false;//marcamos  que el timer termino
                        }};
                        //asignamos que se mueva en estos seguntos para que no cambie de golpe , para que la animacion sea mas suave
                        timer.schedule(tarea, 1000);// decimos al timer que ejecute el TimeTask en los seguntos
                    }
                }
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

            
    public String[] ficheroAArray(){
        String linea;
        ArrayList<String> lineas = new ArrayList();
        try {
            FileReader f = new FileReader(FICHERO);
            BufferedReader b = new BufferedReader(f);
            b.readLine();//ignoramos la 1º linea
            while((linea = b.readLine())!=null) {
                lineas.add(""+linea);//movimientos;tiempo;imagen;nombre
            }
            b.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logica.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String linea1 : lineas) {
            System.out.println(linea1);
        }
        System.out.println("==========================");
        return (String[]) lineas.toArray(new String[lineas.size()]);
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
