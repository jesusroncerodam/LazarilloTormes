/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;

import controladores.ControladorPascua;

/**
 *
 * @author Guille
 */
public class LogicaPascua {
    private ControladorPascua controlador;

    public LogicaPascua(ControladorPascua controlador) {
        this.controlador = controlador;
    }
    
    public void accionEjecutada(String accion){
        System.out.println(accion);
    }
}
