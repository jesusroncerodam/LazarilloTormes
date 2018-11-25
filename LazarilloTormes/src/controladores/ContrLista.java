package controladores;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vista.VLista;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrLista extends MouseAdapter {

    private VLista vistaLista;
    private Logica logica;

    /**
     * Construcyor de Controlador
     * @param vista  vista a la que se refiere, para poder comunicarse con ella
     * @param logica logica a la que se refiere para comunicarse
     */
    public ContrLista(VLista vista, Logica logica) {
        this.vistaLista = vista;
        this.logica = logica;
    }


    /**
     * Metodo encargado de igualar en logica el controlador local con el
     * original
     */
    public void asignarControlador() {
        logica.asignarContrLista(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.listaClick(e.getComponent());
    }


    /**
     * Recoge el fichero en un array
     * @return array de string con el fichero
     */
    public String[] datosFichero() {
        asignarControlador();//solo se realiza una vez
        return logica.ficheroAArray();
    }


    /**
     * Metodo utilizado para llamar al metodo encargado de cambiar de vista
     * @param vistaDestino String con el nombre de la vista a la que vamos a
     *                     cambiar
     */
    public void cambiarVista(String vistaDestino) {
        vistaLista.cambiarVista(vistaDestino);
    }
}
