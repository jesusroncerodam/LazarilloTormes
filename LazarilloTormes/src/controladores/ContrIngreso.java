/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import vista.VIngreso;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrIngreso {

    private VIngreso vista;
    private Logica logica;


    public ContrIngreso(VIngreso vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }

}
