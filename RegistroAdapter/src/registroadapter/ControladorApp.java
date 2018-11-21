/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Guille
 */
public class ControladorApp extends MouseAdapter implements ActionListener,KeyListener{
    private VistaApp vista;
    private Logica logica;
    
    public ControladorApp(VistaApp vista,Logica logica) {
        this.vista = vista;
        this.logica=logica;
        logica.setControladorApp(this);
    }
    public ImageIcon getImagen(){
        return logica.getImagen();
    }
    public String getName(){
        return logica.getName();
    }
    public String getAge(){
        return logica.getAge();
    }
    

    @Override
    public void mouseExited(MouseEvent e) {
        logica.accionApp("sale");
        //System.out.println("salio  "+e);   
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        logica.accionApp("entra");
       //System.out.println("entro  "+e);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        logica.accionApp("boton");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        logica.tecla(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
          //System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //System.out.println(e);
    }
    
    public void cambiarEstado(boolean estado){
        vista.estadoBoton(estado);
    }
    public void pulsoBoton(){
        vista.pulsoBoton();
    }
}
