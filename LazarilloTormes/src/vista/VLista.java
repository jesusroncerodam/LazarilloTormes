/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrLista;
import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VLista extends JPanel{

    private ContrLista controlador;
    private JTextArea taDatos;
    private JButton atras;
    
    public VLista(Logica logica) {
        controlador = new ContrLista(this, logica);
    }
    public void generar(){
        taDatos=new JTextArea;
    }

}
