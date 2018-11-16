/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrLista;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VLista extends JPanel{
    private final String[] NOMBRE_COLUMNAS = {"Nº","Image","Name","Mov.","Time"};
    private String[] datos;
    Object[][] columna;
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
        ingesarDatos();
        
        modelo = new DefaultTableModel(columna,NOMBRE_COLUMNAS);
        tabla = new JTable(modelo);
        tabla.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer("/img/2.jpg"));
//        System.out.println(tabla.getColumnCount());
//        File f=new File("src/img/carta.jpg");
//        System.out.println(f.getAbsoluteFile());
//        tabla.getColumnModel().getColumn(4).setCellRenderer((TableCellRenderer) new ImageIcon(getClass().getResource("src/img/carta.jpg")));//new ImageIcon("src/img/carta.jpg")
                //new ImageIcon(getClass().getResource(iconName)); 
 
        tabla.setPreferredScrollableViewportSize(new Dimension(250, 100));
        JScrollPane scrollPane = new JScrollPane(tabla);
        this.add(scrollPane);

      //  getContentPane().add(scrollPane, BorderLayout.CENTER);
        //cabecera();
        /*
        //Document modelo=new Document
        taDatos=new JTextPane();
        attrs = new SimpleAttributeSet();

        modelo = new DefaultTableModel(datos.length,NOMBRE_COLUMNAS.length);
        tabla = new JTable(modelo);
        cabecera();
        
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
    public void ingesarDatos(){//{"Nº","Image","Name","Mov.","Time"};
        columna=new Object[datos.length][NOMBRE_COLUMNAS.length];
        String[] elementos;
        for (int i = 0; i < datos.length; i++) {
            elementos=datos[i].split(";");
            columna[i][0]=new Integer(i+1);
            for (int j = 0,k=1; j < elementos.length; j++,k++) {//movimientos;tiempo;imagen;nombre
                //System.out.println(elementos[j]+" -i"+i+" -j"+j);
                switch (j) {    
                    case 0://movimientos
                        //lo colocamos en el array
                        columna[i][3]=new ImageIcon(elementos[j]); 
                        break;
                    case 1://tiempo                        
                        columna[i][4]=elementos[j];
                        break;
                    case 2://imagen
                        columna[i][1]=new ImageIcon(getClass().getResource("/img/2.jpg"));//new JLabel(new ImageIcon(elementos[j])); //new Integer(elementos[j]);
                        break;
                    case 3://nombre               
                        columna[i][2]=elementos[j];//new Integer(elementos[j]);
                        break;
                    default:
                        System.out.println("error "+j);
                }
            }
        }
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
class ImageRenderer extends DefaultTableCellRenderer {
        ImageIcon icon = null;

        ImageRenderer(String iconName) {
            icon = new ImageIcon(getClass().getResource(iconName));
        }
  }
 /*String[] campos;
        Object[] columna=new Object[NOMBRE_COLUMNAS.length];
        for (int i = 0; i < datos.length; i++) {
            campos=datos[i].split(";");
            System.out.println(datos.toString());
            for (int j = 0; j < campos.length; j++) {
                System.out.println(datos[j]);
                if(j==2){
                    columna[j]=new ImageIcon(campos[2]); 
                }else
                    columna[j]=datos[j];
            }
            añadirColumna(campos);
        }*/
        //int i=0;//provisional
        //movimientos;tiempo;imagen;nombre
        
       // ImageIcon imagen=new ImageIcon(campos[2]); //campos 2 es la url donde essta la imagen
    