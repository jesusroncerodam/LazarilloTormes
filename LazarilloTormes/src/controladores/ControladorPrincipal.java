/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import trabajodi.Logica;
import vista.VPrincipal;


/**
 *
 * @author Guille
 */
public class ControladorPrincipal extends MouseAdapter {

    private Logica logica;
    private VPrincipal vistaPrincipal;


    public ControladorPrincipal(Logica logica, VPrincipal vista) {
        this.logica = logica;
        this.vistaPrincipal = vista;
        logica.asignarContrPrincipal(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.principalClick(((JButton) e.getComponent()).getText(), e.getClickCount());//e.getClickCount()
    }


    /**
     * Metodo se le llama desde la logica para cambiar de vista
     * @param vistaDestino String que corresponde al nombre de la vista de
     *                     destino
     */
    public void cambiarDeVista(String vistaDestino) {
        vistaPrincipal.cambiarDeVista(vistaDestino);
    }
}
