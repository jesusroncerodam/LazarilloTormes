/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Guille
 */
public class VPrincipal extends JLabel {

    private ContrPrinipal controlador;
    private GridBagConstraints constrain;
    private JButton bNuevaPartida, bEstadisticas, bCargarPartida, bDialogoModal;
    private ImageIcon fondo = new ImageIcon(this.getClass().getResource("/img/fondoPrinc.jpg"));


    public VPrincipal(Logica logica) {
        controlador = new ContrPrinipal(logica, this);
    }


    public void generar() {
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocus();

        constrain = new GridBagConstraints();
        
         //constrain.fill = GridBagConstraints.BOTH;
         constrain.anchor = GridBagConstraints.CENTER;
       constrain.weighty = 1.0; //para que se estiren las columnas
       
        constrain.weightx= 1.0; // El área de texto ocupa 1 filas.
        // constrain.weightx=1;
         // constrain.fill = GridBagConstraints.BOTH;
       // constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());
        btnPartida();
        btnCargarPartida();
        btnEstadisticas();
        btnDialogModal();
        btnExtra();
        repaint();
    }


    private void btnPartida() {
        bNuevaPartida = new JButton("New Game");
        constrain.gridx = 0; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1
      //  constrain.gridwidth = 1; // El área de texto ocupa dos columnas.
      //  constrain.gridheight = 1; // El área de texto ocupa 1 filas.
      //  constrain.fill = GridBagConstraints.BOTH; //para que no se expanda
        //constrain.weighty = 0.0;//`no deje espacio en el eje Y
        
        this.add(bNuevaPartida, constrain);
    }


    private void btnEstadisticas() {
        bEstadisticas = new JButton("Estadisticas");
        constrain.gridx = 2; // El área de texto empieza en la columna 0.
        constrain.gridy = 2; // El área de texto empieza en la fila 1
       // constrain.gridwidth = 1; // El área de texto ocupa dos columnas.
        //constrain.gridheight = 1; // El área de texto ocupa 1 filas.
        //constrain.fill = GridBagConstraints.NONE; //para que no se expanda
       // constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(bEstadisticas,constrain);
    }


    private void btnCargarPartida() {
        bCargarPartida = new JButton("Cargar Partida");
        constrain.gridx = 1; // El área de texto empieza en la columna 0.
        constrain.gridy = 2; // El área de texto empieza en la fila 1
//        constrain.gridwidth = 1; // El área de texto ocupa dos columnas.
//        constrain.gridheight = 1; // El área de texto ocupa 1 filas.
//        constrain.fill = GridBagConstraints.NONE; //para que no se expanda
       // constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(bCargarPartida,constrain);
    }


    private void btnDialogModal() {
        bDialogoModal = new JButton("Dialogo Modal");
        constrain.gridx = 3; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1
//        constrain.gridwidth = 1; // El área de texto ocupa dos columnas.
//        constrain.gridheight = 1; // El área de texto ocupa 1 filas.
       // constrain.fill = GridBagConstraints.NONE; //para que no se expanda
      //  constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(bDialogoModal,constrain);
    }//fondoPrinc.jpg

    private void btnExtra() {
        JButton bExtrs = new JButton("extra");
        constrain.gridx = 1; // El área de texto empieza en la columna 0.
        constrain.gridy = 3; // El área de texto empieza en la fila 1
       constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
//        constrain.gridheight = 1; // El área de texto ocupa 1 filas.
     //   constrain.fill = GridBagConstraints.NONE; //para que no se expanda
     //   constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(bExtrs,constrain);
    }//fondoPrinc.jpg

    public void paint(Graphics g) {
      
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
          super.paint(g);//borramos la imagen anterior
    }
}
