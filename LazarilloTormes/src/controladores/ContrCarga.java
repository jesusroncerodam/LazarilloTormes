/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import vista.VCarga;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrCarga {

    private VCarga vista;
    private Logica logica;


    public ContrCarga(VCarga vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }

}
