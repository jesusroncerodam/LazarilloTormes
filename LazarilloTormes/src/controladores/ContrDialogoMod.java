/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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


    public void mandarControlador() {
        logica.asignarControladorDialogoMod(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "botonAtras":
                cambiarVista("principal");
                break;

            default:
                System.err.println("\nOpcion no valida");
        }
    }


    public void cambiarVista(String vista) {
        vDialog.cambiarDeVista(vista);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.vistaDialogoModMouseListener(e);
    }
    
}
