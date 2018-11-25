/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vista.VDialogoMod;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrDialogoMod extends MouseAdapter implements ActionListener {

    private VDialogoMod vDialog;
    private Logica logica;


    public ContrDialogoMod(VDialogoMod vista, Logica logica) {
        this.vDialog = vista;
        this.logica = logica;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.vistaDialogoModMouseListener(e);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        logica.vistaDialogoModActionListener(e);
    }


    /**
     * metodo encargado de llamar al metodo alojado en logica que se encarga de
     * igualar el controlador local al que se le pasa por parametro
     */
    public void mandarControlador() {
        logica.asignarControladorDialogoMod(this);
    }


    /**
     * Metodo utilizado para llamar al metodo encargado de cambiar de vista
     * @param vistaDestino String con el nombre de la vista a la que vamos a cambiar
     */
    public void cambiarVista(String vistaDestino) {
        vDialog.cambiarDeVista(vistaDestino);
    }
}
