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
import vista.VIngreso;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrIngreso extends MouseAdapter {

    private VIngreso vistaIngreso;
    private Logica logica;


    public ContrIngreso(VIngreso vista, Logica logica) {
        this.vistaIngreso = vista;
        this.logica = logica;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getComponent().getName()) {
            case "botonFlechaAtras":

                vistaIngreso.cambiarDeVista("principal");

                break;

            case "botonFlechaSiguiente":

                vistaIngreso.cambiarDeVista("juego");

                break;

            default:
                System.err.println("\nOpcion no valida");
        }
    }
}
