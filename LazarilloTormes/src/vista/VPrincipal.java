/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ContrPrinipal;
import javax.swing.JLabel;
import trabajodi.Logica;

/**
 *
 * @author Guille
 */
public class VPrincipal extends JLabel{
    
    private ContrPrinipal controlador;


    public VPrincipal(Logica logica) {
        controlador = new ContrPrinipal(logica,this );
    }
    public void generar(){
        
    }
}
