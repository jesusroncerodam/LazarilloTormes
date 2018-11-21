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
    private JPanel panel1;
    private final int ANCHOMARCO = 500, ALTOMARCO = 500;
    private JLabel labelNombre, labelApodo;
    private TextField campoNombre;
    private JButton botonFlechaAtrás, avanzarPagina;
    /*
     *
     *
     *
     */
    private ImageIcon imagenFlecha = new ImageIcon(this.getClass().getResource("/img/flecha.png"));
    /**
     *
     *
     *
     *
     *
     */
    private GridBagConstraints constrain;
    private GridBagLayout gridLayout;


    /*
     *
     *
     */
    public VIngreso(Logica logica) {
        controlador = new ContrIngreso(this, logica);
    }


    public void generar() {

        constrain = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        crearElementos();
        setVisible(true);
    }


    public void crearElementos() {

        labelNombre = new JLabel();
        labelApodo = new JLabel();
        campoNombre = new TextField();

        botonFlechaAtrás = new JButton(imagenFlecha);
        avanzarPagina = new JButton();

        constrain.gridx = 0;
        constrain.gridy = 0;
//        constrain.weighty = 10;
//        constrain.fill = GridBagConstraints.CENTER;

        add(labelNombre, constrain);

        constrain.gridx = 1;
        constrain.gridy = 0;
        add(labelApodo, constrain);

        constrain.gridx = 2;
        constrain.gridy = 0;
        add(campoNombre, constrain);

        constrain.gridx = 0;
        constrain.gridy = 2;
        add(botonFlechaAtrás, constrain);

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
