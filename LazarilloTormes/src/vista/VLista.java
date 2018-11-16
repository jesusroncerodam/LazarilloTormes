/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrLista;
import java.awt.Image;
import java.awt.TextArea;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VLista extends JPanel{
    private final String[] columnNames = {"Nº","Image","Name","Mov.","Time"};
    private String[] datos;
    JTable tabla ;
    DefaultTableModel modelo;
    private ContrLista controlador;
    private JTextPane taDatos;
    private JButton atras;
     SimpleAttributeSet attrs;
    
    public VLista(Logica logica) {
        controlador = new ContrLista(this, logica);
    }
    public void generar(){ 
        recogerDatos();

         modelo = new DefaultTableModel(datos.length,columnNames.length);
        tabla = new JTable(modelo);
        cabecera();
        /*
        //Document modelo=new Document
        taDatos=new JTextPane();
        attrs = new SimpleAttributeSet();

        taDatos.setEditable(false);
        taDatos.setAutoscrolls(true);
        //taDatos.set
        insertar();

        this.add(taDatos);*/
    }
    public void cabecera(){
        modelo.addRow(columnNames);
    }
    public void ingesarDatos(){
        //movimientos;tiempo;imagen;nombre
        
        modelo.addRow(datos);
    }
    public void recogerDatos(){
        /*ArrayList datos=controlador.datosFichero();
        datos.toArray();*/
        datos=controlador.datosFichero();
        
    }
    public void insertar(){
        JLabel pos,img,nombre,mov,tiempo;
        pos=new JLabel("Nº");
        img=new JLabel("img");
        nombre=new JLabel("Name"); 
        mov=new JLabel("Mov.");
        tiempo=new JLabel(cambiarTamano(new ImageIcon("src/img/reloj.png"),10,10));
          
       taDatos.add(pos);
       taDatos.add(img);
       taDatos.add(nombre);
       taDatos.add(mov);
       taDatos.add(tiempo);
    }
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }

}
