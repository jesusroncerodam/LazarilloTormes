/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrIngreso;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trabajodi.Logica;
import trabajodi.Metodos;


/**
 *
 * @author Guille
 */
public class VIngreso extends JPanel {

    private ContrIngreso controlador;
    private Metodos metodo;

    private JFrame marco1;

    private JPanel panel1;
    private final int ANCHOMARCO = 500, ALTOMARCO = 500;
    private JLabel labelNombre, labelApodo;
    private TextField campoNombre;
    private JButton botonFlechaAtrás, avanzarPagina;

    private GridBagConstraints constrain;
    private GridBagLayout gridLayout = new GridBagLayout();

    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));


    public VIngreso(Logica logica) {
        controlador = new ContrIngreso(this, logica);
    }


    public void generar() {
        crearElementos();
        setVisible(true);
    }


    public void crearElementos() {

        labelNombre = new JLabel();
        labelApodo = new JLabel();
        campoNombre = new TextField();

        botonFlechaAtrás = new JButton(imagenFlecha);
        avanzarPagina = new JButton();

//        constrain.gridx = 1;
//        constrain.gridy = 1;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;
        add(labelNombre);

        add(labelApodo);

        add(campoNombre);

        add(botonFlechaAtrás);

        botonFlechaAtrás = new JButton();
        add(botonFlechaAtrás);
        botonFlechaAtrás.setBounds(50, 350, 100, 50);
        labelNombre.setBounds(50, 50, 50, 50);
//        constrain = new GridBagConstraints();
//        constrain.gridx = 1;
//        constrain.gridy = 1;
//        constrain.fill = GridBagConstraints.HORIZONTAL;
//        constrain.weighty = 0.5;
//        JLabel mainPanel = new JLabel(fondo) {
//            @Override
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                Dimension lmPrefSize = getLayout().preferredLayoutSize(this);
//                size.width = Math.max(size.width, lmPrefSize.width);
//                size.height = Math.max(size.height, lmPrefSize.height);
//                return size;
//            }
//        };
//        mainPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.weightx = 1.0;
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//
//        // Let's put a filler bottom component that will push the rest to the top
//        gbc.weighty = 1.0;
//        mainPanel.add(Box.createGlue(), gbc);
//        add(mainPanel);
//        pack();
    }
}
