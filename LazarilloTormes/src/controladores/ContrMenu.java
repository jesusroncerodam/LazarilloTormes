/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VMenu;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrMenu implements ActionListener {

    private Logica logica;
    private VMenu vista;


    public ContrMenu(Logica logica, VMenu vista) {
        this.logica = logica;
        this.vista = vista;
    }

    public void asignarMenuLogica(){
        logica.asignarMenu(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logica.gestionarMenu(e.getActionCommand());
    }
    public void cambiarVista(String vistaCambio){
        vista.cambiarVista(vistaCambio);
    }
    public boolean estadoSonido(){
        return vista.estadoSonido();
    }
}
