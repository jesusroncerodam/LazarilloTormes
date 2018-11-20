/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import trabajodi.Logica;
import vista.VPrincipal;

/**
 *
 * @author Guille
 */
public class ContrPrinipal implements MouseListener{
    private Logica logica;
    private VPrincipal vista;


    public ContrPrinipal(Logica logica, VPrincipal vista) {
        this.logica = logica;
        this.vista = vista;
        logica.asignarContrPrincipal(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        logica.principalClick(((JButton) e.getComponent()).getText(),e.getClickCount());//e.getClickCount()
    }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public void cambiarDeVista(String cambioVista){
        vista.cambiarDeVista(cambioVista);
    }
    
    
}
