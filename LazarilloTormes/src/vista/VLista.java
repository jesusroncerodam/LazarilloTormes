/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrLista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
     GridBagConstraints loc;
    // scroll;
    public VLista(Logica logica) {
        this.setLayout(new GridBagLayout());
        loc = new GridBagConstraints();
        loc.weighty = 0.5;
        loc.anchor = GridBagConstraints.CENTER;
        controlador = new ContrLista(this, logica);
    }
    public void generar(){ 
        recogerDatos();
      a();
    }
    public void a(){//{"Nº","Image","Name","Mov.","Time"};
        
        JPanel lista=new JPanel(new GridLayout(datos.length+1,1,0,2));//+1 por la primera columna, por el "encabezado
        lista.setBackground(Color.white);
        String[] elementos;
        JPanel primeraFila =new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length+1));
        primeraFila.add(new JLabel());
        for (int i = 0; i < NOMBRE_COLUMNAS.length; i++) {
            JLabel a =new JLabel(NOMBRE_COLUMNAS[i]);
            a.setBackground(Color.red);
            if(i==4)
                a=new JLabel(cambiarTamano(new ImageIcon("src/img/reloj.png"), 20, 20),SwingConstants.LEFT);
            primeraFila.add(a);
        }
        
        lista.add(primeraFila);
        for (int i = 0; i < datos.length; i++) {
            JPanel fila =new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length+1));
            elementos=datos[i].split(";");
            fila.add(new JLabel());
            fila.add(new JLabel(""+i));
            for (int j = 0; j < elementos.length; j++) {//movimientos0;tiempo1;imagen2;nombre3
                //System.out.println(elementos[j]+" -i"+i+" -j"+j);
                switch (j) {    
                    case 0://imagen, en el array es la pos 2
                        JLabel b=new JLabel(cambiarTamano(new ImageIcon(elementos[2]), 20, 20),SwingConstants.LEFT);
                        fila.add(b);
                        //columna[i][3]=new ImageIcon(elementos[j]); 
                        break;
                    case 1://nombre, en el array es la pos 3
                        fila.add(new JLabel(elementos[3]));
                        //columna[i][4]=elementos[j];
                        break;
                    case 2://movimientos, en el array es la pos 0
                        fila.add(new JLabel(elementos[0]));
                        //columna[i][1]=new ImageIcon(getClass().getResource("/img/2.jpg"));//new JLabel(new ImageIcon(elementos[j])); //new Integer(elementos[j]);
                        break;
                    case 3://tiempo, en el array es la pos 1
                        fila.add(new JLabel(elementos[1]));
                        //columna[i][2]=elementos[j];//new Integer(elementos[j]);
                        break;
                    default:
                        System.out.println("error "+j);
                }
            }
            lista.add(fila);
        }
        this.setBorder(LineBorder.createBlackLineBorder());
        lista.setBorder(LineBorder.createBlackLineBorder());
        JScrollPane scroll = new JScrollPane(lista,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(500, 500));
        loc.fill= GridBagConstraints.BOTH;
        loc.gridx = 0;
        loc.gridy = 1;
        loc.weighty = 1;
        this.add(scroll, loc);
      }
     
     public void recogerDatos(){
        datos=controlador.datosFichero();
        
    }
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }
    
    
    
    
    
    
    
    public void test(){
        recogerDatos();
        ingesarDatos();
        
        modelo = new DefaultTableModel(columna,NOMBRE_COLUMNAS);
        tabla = new JTable(modelo);
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
    