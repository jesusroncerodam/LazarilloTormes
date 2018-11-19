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

    /**
     * Construcyor de Controlador del juego se asigna la vista y la logca
     * @param vista vista a la que se refiere, para poder comunicarse con ella
     * @param logica logica a la que se refiere para comunicarse
     */
    public ContrJuego(VJuego vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }
    
    /**
     * Encargado de mandar a la logica la referencia del controlador para poder
     * comunicarse con la vista a través de esta
     */
    public void asignarControlador(){
        this.logica.asignarContrJuego(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        vista.eliminarElementos();
        logica.juegoClick(e.getComponent());
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
    
    
    /**
     * Comunicacion de logica a vista, manda una accion sobre el contador de tiempo
     * @param accion 
     */
    public void gestionarContador(String accion){
        vista.gestionarContador(accion);
    }
    /**
     * Comunicacion de logica a vista, hace animar una imagen , que entre o que salga
     * @param i indice a girar
     */
    public void girar(int i){
        vista.girar(i);
    }
    /**
     * Comunicacion de logica a vista, retorna si hay alguna carta que no este bloqueda
     * y que este visible
     * @return indice de la carta, -1 en caso de que no tenga ninguna visible
     */
    public int algunaVisible(){
        return vista.algunaVisible();
    }
    /**
     * Comunicacion de logica a vista, compara si las imagenes son las mismas
     * @param i indice de la carta a comparar
     * @param j indice de la carta a comparar
     * @return boolean true en caso de que sean la misma carta
     */
    public boolean mismaImagen(int i,int j){
        return vista.mismaImagen(i,j);
    }
    /**
     * Comunicacion de logica a vista, bloquea la imagen para que no se poducan 
     * mas animaciones ni cuente esa imagen para hacer click en ella
     * @param i indice de la carta a bloquear
     * @param j indice de la carta a bloquear
     */
    public void bloquearImagenes(int i, int j){
        vista.bloquearImagenes(i, j);
    }
    /**
     * Comunicacion de logica a vista, suma uno al contador de movimientos
     * y lo muestra
     */
    public void movimiento(){
        vista.movimiento();
    }
    /**
     * Comunicacion de logica a vista, retorna si el juego a terminado
     * @return boolean false si es el fin del juego
     */
    public boolean isFin(){
        return vista.isFin();
    }
    /**
     * Comunicacion de logica a vista, cambia el estado de un boton, lo habilita o desabilita
     * @param boton String nombre del boton a cambiar el estado
     * @param estado boolean, estado a cambiar
     */
    public void cambiarEstadoBoton(String boton,boolean estado){
        vista.cambiarEstadoBoton(boton, estado);
    }
    /**
     * Comunicacion de logica a vista, retorna los segundos actualess
     * @return int seguntos
     */
    public int getContadorSeg() {
        return vista.getContadorSeg();
    }
    /**
     * Comunicacion de logica a vista, retorna los movimientos actuales
     * @return int movimiento
     */
    public int getContMov() {
        return  vista.getContMov();
    }
}
