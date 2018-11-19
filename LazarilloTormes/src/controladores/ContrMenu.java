/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VMenu;
import trabajodi.Logica;


/**

 @author Guille
 */
public class ContrMenu implements ActionListener{

    private Logica logica;
    private VMenu vista;


    public ContrMenu(Logica logica, VMenu vista) {
        this.logica = logica;
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("guardar")){
            vista.guardar();
        }
        if(e.getActionCommand().equals("cargar")){
            vista.cargar();
        }
    }

}
