/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import trabajodi.HuevoPascua;

/**
 *
 * @author Guille
 */
public class ControladorPascua extends WindowAdapter implements ActionListener{
    HuevoPascua vista;

    public ControladorPascua(HuevoPascua vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
