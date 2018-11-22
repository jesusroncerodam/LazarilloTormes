/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import javax.swing.JCheckBox;
import vista.VIngreso;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrIngreso extends MouseAdapter implements TextListener, ItemListener {

    private VIngreso vistaIngreso;
    private Logica logica;


    public ContrIngreso(VIngreso vista, Logica logica) {
        this.vistaIngreso = vista;
        this.logica = logica;
    }


    public void mandarControlador() {
        logica.asignarControladorIngreso(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        switch (e.getComponent().getName()) {
            case "botonFlechaAtras":
                vistaIngreso.cambiarDeVista("principal");
                break;

            case "botonFlechaSiguiente":
                vistaIngreso.cambiarDeVista("juego");
                break;

            case "avatar":
                logica.cogerImagenSistema();
                break;

            default:
                System.err.println("\nOpcion no valida");
        }
    }


    @Override
    public void textValueChanged(TextEvent e) {
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        logica.vistaIngresoItemChange(e);
    }


    public void asignarBordeAvatar(int parseInt) {
        vistaIngreso.asignarBordeAvatar(parseInt);
    }


    public void asignarBordeTema(int parseInt) {
        vistaIngreso.asignarBordeTema(parseInt);

    }
}
