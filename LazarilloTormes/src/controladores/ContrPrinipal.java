/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import trabajodi.Logica;
import vista.VPrincipal;

/**
 *
 * @author Guille
 */
public class ContrPrinipal {
    private Logica logica;
    private VPrincipal vista;


    public ContrPrinipal(Logica logica, VPrincipal vista) {
        this.logica = logica;
        this.vista = vista;
    }
}
