package lazarilloTormes;

import controladores.ControladorPascua;

/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class LogicaPascua {
    private ControladorPascua controlador;
    private int contador;
    
    /**
     * Constructor de logica se asigna Controlador para poder comunicarse con el
     * @param controlador Controlador, al que cominica
     */
    public LogicaPascua(ControladorPascua controlador) {
        this.controlador = controlador;
    }
    
    /**
     * Metodo encargado de gestionar los eventos realizados por el controlador
     * en caso de llegar a 10 acciones, se cierra el Jframe
     * @param accion String, corresponde al indice del boton 
     */
    public void accionEjecutada(String accion){
        contador++;
        controlador.subirBoton(Integer.parseInt(accion));
        if(contador>10)
            controlador.cerrarVista();
    }
    
}
