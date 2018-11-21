/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Guille
 */
public class Vista {
    private Logica logica;
    private JFrame ventana=new JFrame();
    private JPanel pVentana; 
    public Vista(Logica logica) {
        this.logica = logica;
        
        registro();//la primera ve que se ejecuta ponemos la vista de registro
       
        ventana.setVisible(true);
    } 
    
    public void registro(){
        pVentana = new JPanel();//lo generamos
        //pVentana.setBackground(Color.red);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//aqui cuando se da a la x se cierra
        //ventana.setLayout(new FlowLayout(0));
        ventana.add(pVentana);
        ventana.setSize(1100, 850);
        VistaRegistro registo =new VistaRegistro(pVentana, this,logica);
    }
    public void app(){
        //System.out.println("gola");
        //pVentana=null;
       // pVentana = new JPanel();//lo generamos , asi estaria vacio
        //ventana.remove(ventana);
        pVentana.removeAll();
        
        //pVentana = new JPanel();
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.addWindowListener(new GestorWindow());
        ventana.add(pVentana);
        //ventana.setSize(850, 850);
        VistaApp app =new VistaApp(this, pVentana, logica);
        pVentana.repaint();
        ventana.setVisible(true);
    }
    public Logica getLogica(){
        return logica;
    }
    
    
    public void vistaFinal(){
        pVentana.removeAll();
        
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//aqui cuando se da a la x se cierra
        ventana.add(pVentana);
        ventana.setSize(1100, 850);
        VistaFinal utima =new VistaFinal(pVentana);
        
        pVentana.repaint();
        ventana.setVisible(true);
    }
}
