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
    private final String[] NOMBRE_COLUMNAS = {"Nº","Image","Name","Mov.","Time"};
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

         modelo = new DefaultTableModel(datos.length,NOMBRE_COLUMNAS.length);
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
        this.add(tabla);
    }
    public void cabecera(){
        for (String col : NOMBRE_COLUMNAS) {
            modelo.addColumn(col);
        }
    }
    public void ingesarDatos(){
        String[] campos;
        Object[] columna=new Object[NOMBRE_COLUMNAS.length];
        for (int i = 0; i < datos.length; i++) {
            campos=datos[i].split(";");
            for (int j = 0; j < campos.length; j++) {
               /* if(j==2){
                    columna[j]=new ImageIcon(campos[2]); 
                }else*/
                    columna[j]=datos[i];
            }
            añadirColumna(campos);
        }
        int i=0;//provisional
        //movimientos;tiempo;imagen;nombre
        
       // ImageIcon imagen=new ImageIcon(campos[2]); //campos 2 es la url donde essta la imagen
        
        
    }
    public void añadirColumna(Object[] columna){
        modelo.addRow(columna);
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
