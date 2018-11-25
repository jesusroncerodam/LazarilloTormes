package controladores;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import trabajodi.HuevoPascua;
import trabajodi.LogicaPascua;


/**
 *
 * @author Guille
 */
public class ControladorPascua extends WindowAdapter implements ActionListener {

    private HuevoPascua vistaHuevoPascua;
    private LogicaPascua logica;

    
    /**
     * Construcyor de Controlador del juego se asigna la vista 
     * @param vista  vista a la que se refiere, para poder comunicarse con ella
     */
    public ControladorPascua(HuevoPascua vista) {
        this.vistaHuevoPascua = vista;
        logica = new LogicaPascua(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        logica.accionEjecutada(e.getActionCommand());
    }


    @Override
    public void windowClosing(WindowEvent e) {
        cerrarVista();
    }


    public void subirBoton(int indice) {
        vistaHuevoPascua.subirBoton(indice);
    }


    public void cerrarVista() {
        vistaHuevoPascua.cerrarVista();
    }
}
