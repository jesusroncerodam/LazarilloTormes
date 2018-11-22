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

            case "avatar":
                System.out.print("ñaksjfñajsdfñlkajsfñlkasjkfdñljaskñfjklasdjfsd");

                logica.cogerImagenSistema();
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
        if (((JCheckBox) e.getSource()).isSelected()) {
            String variableTexto = ((JCheckBox) e.getSource()).getName();
            switch (variableTexto) {
                case "avatar1":
                    System.out.print("Avatar 1");

                case "avatar2":
                case "avatar3":
                    vistaIngreso.asignarBordeAvatar(Integer.parseInt(variableTexto.substring(6)));
                    break;

                case "tema1":
                case "tema2":
                case "tema3":
                    vistaIngreso.asignarBordeTema(Integer.parseInt(variableTexto.substring(4)));
                    break;

                case "dificultad1":
                    System.out.println("dificultad facil");
                case "dificultad2":
                    System.out.println("dificultad media");
                case "dificultad3":
                    System.out.println("dificultad dificl");
                    break;

                default:
                    System.err.println("\nOpcion no valida");
            }
        }
    }
}
