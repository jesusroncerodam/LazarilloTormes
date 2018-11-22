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


    @Override
    public void textValueChanged(TextEvent e) {
        System.out.println(e);

    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        int state = e.getStateChange();
        if (state == ItemEvent.SELECTED) {
            System.out.println(state);

        }
if(((JCheckBox) e.getSource()).isSelected())
        switch (((JCheckBox) e.getSource()).getName()) {
            case "tema1":
                System.out.println("Has elegido el tema 1");

                break;

            case "tema2":
                System.out.println("Tema 2");

                break;

            case "tema3":
                System.out.println("Tema 3");

                break;

            case "facil":
                System.out.println("dificultad facil");

                break;

            case "medio":
                System.out.println("dificultad media");

                break;

            case "dificil":
                System.out.println("dificultad dificl");

                break;

            default:
                System.err.println("\nOpcion no valida");
        }
    }
}
