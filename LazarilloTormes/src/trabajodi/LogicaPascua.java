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
    private int contador;

    public LogicaPascua(ControladorPascua controlador) {
        this.controlador = controlador;
    }
    
    public void accionEjecutada(String accion){
        contador++;
        controlador.subirBoton(Integer.parseInt(accion));
        if(contador>10)
            controlador.cerrarVista();
    }
    
}
