/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package controladores;


import java.util.ArrayList;
import vista.VLista;
import trabajodi.Logica;


/**

 @author Guille
 */
public class ContrLista {

    private VLista vista;
    private Logica logica;


    public ContrLista(VLista vista, Logica logica) {
        this.vista = vista;
        this.logica = logica;
    }
    public String[] datosFichero(){
        return logica.ficheroAArray();
    }

}
