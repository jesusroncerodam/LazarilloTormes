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
    private VMenu vistaMenu;


    public ContrMenu(Logica logica, VMenu vista) {
        this.logica = logica;
        this.vistaMenu = vista;
    }


    /**
     * Metodo encargado de igualar en logica el controlador local con el
     * original
     */
    public void asignarMenuLogica() {
        logica.asignarMenu(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        logica.gestionarMenu(e.getActionCommand());
    }


    /**
     * Metodo se le llama desde la logica para cambiar de vista
     * @param vistaDestino String que corresponde al nombre de la vista de
     *                     destino
     */
    public void cambiarVista(String vistaDestino) {
        vistaMenu.cambiarVista(vistaDestino);
    }


    public boolean estadoSonido() {
        return vistaMenu.estadoSonido();
    }
}
