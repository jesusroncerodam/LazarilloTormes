/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import trabajodi.HuevoPascua;
import trabajodi.LogicaPascua;

/**
 *
 * @author Guille
 */
public class ControladorPascua extends WindowAdapter implements ActionListener{
    private HuevoPascua vista;
    private LogicaPascua logica;

    public ControladorPascua(HuevoPascua vista) {
        this.vista = vista;
        logica=new LogicaPascua(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logica.accionEjecutada(e.getActionCommand());
    }
    
    public void subirBoton(int indice){
        vista.subirBoton(indice);
    }


    @Override
    public void windowClosing(WindowEvent e) {
        cerrarVista();
    }
    public void cerrarVista(){
        vista.cerrarVista();    
    }
    
}
