/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrPrinipal;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VPrincipal extends JLabel {

    private ContrPrinipal controlador;
    private GridBagConstraints constrain;
    private JButton bNuevaPartida, bEstadisticas, bCargarPartida, bDialogoModal;
    private ImageIcon fondo=new ImageIcon(this.getClass().getResource("/img/fondoPrinc.jpg"));

    public VPrincipal(Logica logica) {
        controlador = new ContrPrinipal(logica, this);
    }


    public void generar() {
        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();

        constrain = new GridBagConstraints();
        constrain.weighty = 0.5; //para que se estiren las columnas
        // constrain.weightx=1;
        //  constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());
repaint();
        btnCargarPartida();
        btnEstadisticas();
        btnPartida();
        btnDialogModal();
        //setVisible(true);
    }


    private void btnPartida() {
        bNuevaPartida = new JButton("Nueva partida");
        this.add(bNuevaPartida);
    }


    private void btnEstadisticas() {
        bEstadisticas = new JButton("Estadisticas");
        this.add(bEstadisticas);
    }


    private void btnCargarPartida() {
        bCargarPartida = new JButton("Cargar Partida");
        this.add(bCargarPartida);
    }


    private void btnDialogModal() {
        bDialogoModal = new JButton("Dialogo Modal");
        this.add(bDialogoModal);
    }//fondoPrinc.jpg
     public void paint(Graphics g) {
        super.paint(g);//borramos la imagen anterior
        g.drawImage( fondo.getImage(), 0,0, getWidth(), getHeight(), null);
    }
}
