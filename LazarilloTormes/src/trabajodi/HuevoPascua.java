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
public class HuevoPascua {
    
    private JFrame ventana;
    private final String IMAGEN1="/img/letras.gif",IMAGEN2="/img/carga.gif",IMAGEN3="/img/nubes.gif",IMAGEN4="/img/nubes.png",IMAGEN_GLOBO="/img/globo.png";
    private JLabel etiqueta;
    private JButton[] boton;
    private final int CANTIDAD_GLOBOS=5;
    private ControladorPascua controlador;
    
    public HuevoPascua() {
        controlador= new ControladorPascua(this);
        ventana=new JFrame("Easter Egg");
        boton=new JButton[CANTIDAD_GLOBOS];
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600,600);
        
        ventana.setVisible(true);
        generar();
    }
    
    public void generar(){
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
    
    public void dormir(int segundos){
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException ex) {
            Logger.getLogger(HuevoPascua.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarImagen(String imagen){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(imagen)).getImage().getScaledInstance(ventana.getWidth(), ventana.getHeight(), Image.SCALE_DEFAULT));
        etiqueta.setIcon(imageIcon);
        imageIcon.setImageObserver(etiqueta); 
        ventana.add(etiqueta);
        etiqueta.updateUI();
    }
    
    
    private void generarJuego(){
        int x=30;
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
    
    private void mover(){
        Timer timer=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=((int) (Math.random() * boton.length) ) ;
                int suma=((int) (Math.random() * 4) +1);
                boton[i].setLocation(boton[i].getLocation().x, boton[i].getLocation().y+suma);
                if(boton[i].getLocation().y>=ventana.getHeight()){
                    boton[i].setLocation(boton[i].getLocation().x,0);
                }
            }
        });
        timer.start();
        timer.setRepeats(true);
    }
    
    /**
     * Metodo cambia el fondo, existe una posibilidad entre 11, cada segundo de 
     * que el fondo sea la IMAGEN3, si no, será la IMAGEN4
     */
    private void fondo(){
        Timer timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(((int) (Math.random() * 11)+1 )==4 ){
                    generarImagen(IMAGEN3);
                }else{
                    generarImagen(IMAGEN4);
                }
            }
        });
        timer.start();
        timer.setRepeats(true);
    }
}
