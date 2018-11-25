/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

import controladores.ControladorPascua;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Guille
 */
public class HuevoPascua extends Thread{
    
    private JFrame ventana;
    private final String IMAGEN1="/img/letras.gif",IMAGEN2="/img/carga.gif",IMAGEN3="/img/nubes.gif",IMAGEN4="/img/nubes.png",IMAGEN_GLOBO="/img/globo.png";
    private JLabel etiqueta;
    
    private JButton[] boton;
    private final int CANTIDAD_GLOBOS=5;
    private ControladorPascua controlador;
    private boolean salir;
    private Timer cambiarfondo,timerMover;
    
    /**
     * Constructor, genera los elementos, asi como el jframe y los controladores
     */
    public HuevoPascua() {
        salir=false;
        controlador= new ControladorPascua(this);
        ventana=new JFrame("Easter Egg");
        boton=new JButton[CANTIDAD_GLOBOS];
        ventana.addWindowListener(controlador);
        ventana.setSize(600,600);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
    
    /**
     * Generamos el hilo , llamado a generar
     */
    @Override
    public void run(){
        generar();
    }
    
    /**
     * Generamos todo el contenido de la Vista, y sus animaciones
     */
    private void generar(){
        etiqueta = new JLabel();
        
        generarImagen(IMAGEN1);
        dormir(2000);
        
        ventana.remove(etiqueta);
        generarImagen(IMAGEN2);
        dormir(2000);
        
        ventana.remove(etiqueta);
        generarImagen(IMAGEN3);
        etiqueta.setLayout(null);
        generarJuego();
        fondo();
    }
    
    /**
     * Metodo que duerme la ejecucion del hilo, mientra que salir sea falso
     * @param segundos int, milisegundos 1s=1000milisegundos
     */
    private void dormir(int segundos){
        if(!salir)
            try {
                Thread.sleep(segundos);
            } catch (InterruptedException ex) {
                Logger.getLogger(HuevoPascua.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Generamos la etiqueta con la imagen correspondiente  y con un 
     * ImageObserver para que en caso de ser un gif, cambie
     * @param imagen String, ruta de la imagen
     */
    private void generarImagen(String imagen){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(imagen)).getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        etiqueta.setIcon(imageIcon);
        imageIcon.setImageObserver(etiqueta); 
        ventana.add(etiqueta);
        etiqueta.updateUI();
    }
    
    /**
     * Generamos los botones y los ajustamos en funcion del tamaño de la ventana
     */
    private void generarJuego(){
        int x=10;
        int separacion=ventana.getWidth()/boton.length;
        for (int i = 0; i < boton.length; i++) {
            boton[i]=new JButton(new ImageIcon(this.getClass().getResource(IMAGEN_GLOBO)));
            
            boton[i].addActionListener(controlador);
            boton[i].setActionCommand(""+i);
            
            boton[i].setContentAreaFilled(false);
            boton[i].setBorder(null);
            boton[i].setToolTipText("Click!");
            
            boton[i].setBounds(x, 0, 50, 100);
            x+=separacion;
            
            etiqueta.add(boton[i]);
        }
        etiqueta.updateUI();
        mover();
    }
    
    /**
     *  Creamos un timer para que animar la caida de los globos, el globo que se
     *  mueve es aleatorio junto con la cantidad de pixeles que se mueve
     */
    private void mover(){
        timerMover=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!salir){
                    int i=((int) (Math.random() * boton.length) ) ;
                    int suma=((int) (Math.random() * 4) +1);
                    boton[i].setLocation(boton[i].getLocation().x, boton[i].getLocation().y+suma);
                    if(boton[i].getLocation().y>=ventana.getHeight()){
                        boton[i].setLocation(boton[i].getLocation().x,0);
                    }
                }else{
                    timerMover.stop();
                }
            }
        });
        timerMover.start();
        timerMover.setRepeats(true);
    }
    
    /**
     * Metodo cambia el fondo, existe una posibilidad entre 11, cada segundo de 
     * que el fondo sea la IMAGEN3, si no, será la IMAGEN4
     */
    private void fondo(){
        cambiarfondo=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!salir){
                    if(((int) (Math.random() * 11)+1 )==4 ){
                       generarImagen(IMAGEN3);
                   }else{
                       generarImagen(IMAGEN4);
                   }
                }else{
                    cambiarfondo.stop();
                }
            }
        });
        cambiarfondo.start();
        cambiarfondo.setRepeats(true);
    }
    
    /**
     * Metodo llamado por el controlador, mueve el globo a la posicion 0
     * @param indice int, indice del boton a mover
     */
    public void subirBoton(int indice){
        boton[indice].setLocation(boton[indice].getLocation().x, 0);
    }
    
    /**
     * Metodo ejecutado cuando se cierra la ventana, activa salir, y en caso de 
     * tener algun timer activo, lo para
     */
    public void cerrarVista(){
        if(timerMover!=null)
            timerMover.stop();
        if(cambiarfondo!=null)
            cambiarfondo.stop();
        salir=true;
        etiqueta.removeAll();
        ventana.removeAll();
        ventana.dispose();
    }

    
}
