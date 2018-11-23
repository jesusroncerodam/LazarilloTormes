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
        logica.vistaIngresoMouseListener(e);
    }


    @Override
    public void textValueChanged(TextEvent e) {
        logica.vistaIngresoEscuchadorTexto(e);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        logica.vistaIngresoItemChange(e);
    }


    public void asignarBordeAvatar(int avatar) {
        vistaIngreso.asignarBordeAvatar(avatar);
    }


    public void asignarBordeTema(int tema) {
        vistaIngreso.asignarBordeTema(tema);
    }


    public void asignarBordeDificultad(int dificultad) {
        vistaIngreso.asignarBordeDificultad(dificultad);
    }


    public void cambiarVista(String vista) {
        vistaIngreso.cambiarDeVista(vista);
    }
    
    public void mandarRecogerDatos(){
        vistaIngreso.mandarDatos();
    }
    //recogerAvatar(), recogerTema(),recogerDificultad(),recogerNombre()
    public void mandarDatos(String avatar,int tema, int dificultad, String nombre){
        logica.recogerDatos(avatar, tema, dificultad, nombre);
    }
}
