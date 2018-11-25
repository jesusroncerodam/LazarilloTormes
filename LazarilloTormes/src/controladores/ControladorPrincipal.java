package controladores;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import lazarilloTormes.Logica;
import vista.VPrincipal;


/**
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class ControladorPrincipal extends MouseAdapter {

    private Logica logica;
    private VPrincipal vistaPrincipal;


    /**
     * Construcyor de Controlador del principal
     * @param vista  vista a la que se refiere, para poder comunicarse con ella
     * @param logica logica a la que se refiere para comunicarse
     */
    public ControladorPrincipal(Logica logica, VPrincipal vista) {
        this.logica = logica;
        this.vistaPrincipal = vista;
        logica.asignarContrPrincipal(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.principalClick(((JButton) e.getComponent()).getText(), e.getClickCount());//e.getClickCount()
    }


    /**
     * Metodo se le llama desde la logica para cambiar de vista
     * @param vistaDestino String que corresponde al nombre de la vista de
     *                     destino
     */
    public void cambiarDeVista(String vistaDestino) {
        vistaPrincipal.cambiarDeVista(vistaDestino);
    }
}
