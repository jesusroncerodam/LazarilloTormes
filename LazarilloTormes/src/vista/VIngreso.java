/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrIngreso;
import javax.swing.JPanel;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class VIngreso extends JPanel {

    private ContrIngreso controlador;


    public VIngreso(Logica logica) {
        controlador = new ContrIngreso(this, logica);
    }


    public void generar() {
//        crearElementos();
//        anadirElementos();
//        posicionElementos();
//        estiloElementos();
//        formaMarco();
//        anadirControladores();
    }
}
