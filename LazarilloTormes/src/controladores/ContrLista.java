/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package controladores;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vista.VLista;
import trabajodi.Logica;


/**

 @author Guille
 */
public class ContrLista extends MouseAdapter{

    private VLista vista;
    private Logica logica;
    public ContrLista(VLista vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }
    public void asignarControlador(){
        logica.asignarContrLista(this);
    }
    public String[] datosFichero(){
        asignarControlador();//solo see realiza una vez
        return logica.ficheroAArray();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        logica.listaClick(e.getComponent());
    }
    
    public void cambiarVista(String vistaACambiar){
        vista.cambiarVista(vistaACambiar);
    }

}
