/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import vista.VDialogoMod;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrDialogoMod {

    private VDialogoMod vista;
    private Logica logica;


    public ContrDialogoMod(VDialogoMod vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }

}
