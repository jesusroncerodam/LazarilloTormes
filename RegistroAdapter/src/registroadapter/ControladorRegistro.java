/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import com.sun.xml.internal.bind.v2.model.core.Adapter;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import javax.swing.ImageIcon;

/**
 *
 * @author Guille
 */
public class ControladorRegistro extends MouseAdapter{
    private VistaRegistro vista;
    private Logica logica;
    public ControladorRegistro(VistaRegistro vista,Logica logica) {
        this.vista = vista;
        this.logica=logica;//vista.pasarLogica();
        logica.setControladorReg(this);
        
    }
    public void mouseClicked(MouseEvent e){
        //System.out.println(e.getComponent().getName()+" - "+e.getComponent());
        logica.accionRegistro(e.getComponent().getName());
        // se define el tratamiento que se dará al evento
    }
    public String comprobar(String dato){
        return vista.datos(dato);
    }
    public void deseleccionarImagen(){
        vista.deseleccionarImagenes();
    }
    public void seleccioarImagen(int numero){
        vista.seleccionarImagenes(numero);
    }
    public int anadirImagen(String ruta){
        return vista.anadirImagen(ruta);
    }
    public void estadoBoton(boolean estado){
        vista.estadoBoton(estado);
    }
    public ImageIcon getImagen(int imagen){
        return vista.getImagen(imagen);
    }
    public void crearApp(){
        vista.crearApp();
    }
}
