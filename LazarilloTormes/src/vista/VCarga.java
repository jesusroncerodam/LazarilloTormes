/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrCarga;
import javax.swing.JPanel;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VCarga extends JPanel{

    private ContrCarga controlador;


    public VCarga(Logica logica) {
        this.controlador = new ContrCarga(this, logica);
    }
}
