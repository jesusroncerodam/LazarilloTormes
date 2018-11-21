/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Guille
 */
public class VistaApp {
    private Vista vista;
    private JPanel pVentana;
    private String nombre, age;
    private ImageIcon img;
    //private JLabel lCuadro;
    private JButton boton;
    private JPanel pBoton,pTexto;
    private ControladorApp controlador;

    private Border borde  = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4), BorderFactory.createLoweredBevelBorder());
    private Font nombres = new Font("Agency FB", Font.BOLD, 30);

    public VistaApp(Vista vista, JPanel pVentana, Logica logica) {
        this.vista = vista;
        this.pVentana = pVentana;
        controlador= new ControladorApp(this,vista.getLogica());
        pVentana.setLayout(null);
        //pVentana.setBackground(Color.green);
        pVentana.setFocusable(true);
        pVentana.addKeyListener(controlador);
        insertarDatos();
        
        asignarDatos();
        pVentana.add(pTexto);
        //pTexto.setLocation(10, 10);
        
        pTexto.setBounds(300, 150, 500, 500);
        
        generarBoton();
        pVentana.add(pBoton);
        pBoton.setBounds(0, 500, 1100, 500);
    }
    public void insertarDatos(){
        img=controlador.getImagen();
        nombre=controlador.getName();
        age=controlador.getAge();
    }
    public void asignarDatos(){
        pTexto=new JPanel(new GridLayout(2, 2));
        pTexto.setBorder(borde);
        pTexto.addMouseListener(controlador);
        pTexto.setBackground(Color.LIGHT_GRAY);
        JLabel lNombre=new JLabel("Nombre: "+this.nombre);
        pTexto.add(lNombre );
        lNombre.setFont(nombres);
        JLabel lFoto=new JLabel(img);
        lFoto.setBorder(borde);
        pTexto.add(lFoto);
        JLabel lAge=new JLabel("Edad: "+this.age);
        lAge.setFont(nombres);
        pTexto.add(lAge);
    }
    
    
    
    private void generarBoton(){
        pBoton=new JPanel(new BorderLayout());
        boton=new JButton();
        ImageIcon flecha=new ImageIcon(new ImageIcon(this.getClass().getResource("/fotos/flecha.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        boton.setIcon(flecha);
        
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        //boton.setEnabled(false);
        estadoBoton(false);
        boton.addActionListener(controlador);
        boton.setName("siguiente");
        pBoton.add(boton, BorderLayout.EAST);
        
    }
    public void estadoBoton(boolean estado){
        boton.setEnabled(estado);
    }
    public void pulsoBoton(){
        vista.vistaFinal();
    }
    
     
}
