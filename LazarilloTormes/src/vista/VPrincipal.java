/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controladores.ContrPrinipal;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import trabajodi.Logica;

/**
 *
 * @author Guille
 */
public class VPrincipal extends JLabel{
    private ContrPrinipal controlador;
    private GridBagConstraints constrain;
    private JButton bNuevaPartida,bEstadisticas,bCargarPartida,bDialogoModal;

    public VPrincipal(Logica logica) {
        controlador = new ContrPrinipal(logica,this );
    }
    public void generar(){
        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();
        
        constrain = new GridBagConstraints();
        constrain.weighty = 0.5; //para que se estiren las columnas
        // constrain.weightx=1;
        //  constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());
        
        btnCargarPartida();
        btnEstadisticas();
        btnPartida();
        btnDialogModal();
    }
    private void btnPartida(){
        
    }
    private void btnEstadisticas(){
        
    }
    private void btnCargarPartida(){
        
    }
    private void btnDialogModal(){
        
    }
}
