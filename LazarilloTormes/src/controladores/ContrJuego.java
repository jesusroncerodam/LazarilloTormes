/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package controladores;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.VJuego;
import trabajodi.Logica;


/**

 @author Guille
 */
public class ContrJuego implements MouseListener, KeyListener {

    private VJuego vista;
    private Logica logica;


    public ContrJuego(VJuego vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
        this.logica.asignarContrJuego(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        /*System.out.println(e.getComponent().getName());
        System.out.println(e.getComponent());*/
        logica.juegoClick(e.getComponent());
        
        //vista.algo();
    }


    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e);
    }
    
    
    public void gestionarContador(String accion){
        vista.gestionarContador(accion);
    }
    public void girar(int i){
        vista.girar(i);
    }
    public int algunaVisible(){
        return vista.algunaVisible();
    }
    public boolean mismaImagen(int i,int j){
        return vista.mismaImagen(i,j);
    }
    public void bloquearImagenes(int i, int j){
        vista.bloquearImagenes(i, j);
    }
    public void movimiento(){
        vista.movimiento();
    }
}
