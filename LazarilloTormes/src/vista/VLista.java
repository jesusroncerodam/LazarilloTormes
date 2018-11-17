/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import com.sun.java.swing.plaf.windows.WindowsScrollPaneUI;
import controladores.ContrLista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalScrollPaneUI;
import javax.swing.plaf.multi.MultiScrollPaneUI;
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
    private final Color COLOR_LETRAS=Color.white;
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
        this.setBackground(Color.blue);
        recogerDatos();
      a();
    }
    public void a(){//{"Nº","Image","Name","Mov.","Time"};
        
        JPanel lista=new JPanel(new GridLayout(datos.length+1,1,0,0));//+1 por la primera columna, por el "encabezado
        lista.setBackground(Color.white);
        String[] elementos;
        lista.setOpaque(false);
        JPanel primeraFila =new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length+1));
        primeraFila.add(new JLabel());
        for (int i = 0; i < NOMBRE_COLUMNAS.length; i++) {
            JLabel a =new JLabel(NOMBRE_COLUMNAS[i]);
            a.setForeground(COLOR_LETRAS);
            if(i==4)
                a=new JLabel(cambiarTamano(new ImageIcon("src/img/relojWH.png"), 20, 20),SwingConstants.LEFT);
            primeraFila.add(a);
        }
        primeraFila.setBackground(new Color(47, 92, 255,200));
        //primeraFila.setBorder(LineBorder.createBlackLineBorder());
        lista.add(primeraFila);
        for (int i = 0; i < datos.length; i++) {
            JPanel fila =new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length+1));
            elementos=datos[i].split(";");
            fila.add(new JLabel());
            fila.add(new JLabel(""+i));//47, 92, 255
            if(i%2!=0){
                fila.setBackground(new Color(255, 255, 255,200));
            }else
                 fila.setBackground(new Color(99, 132, 252,200));
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
        //this.setBorder(LineBorder.createBlackLineBorder());
        //lista.setBorder(LineBorder.createBlackLineBorder());
        JScrollPane scroll = new JScrollPane(lista,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estadisticas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12),COLOR_LETRAS));
        scroll.setPreferredSize(new Dimension(500, 500));
        loc.fill= GridBagConstraints.BOTH;
        loc.gridx = 0;
        loc.gridy = 0;
        loc.weighty = 2;
        loc.weightx = 2;
        loc.insets = new Insets(50,25,25,25);  
        lista.setOpaque(false);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        this.add(scroll, loc);
        crearBoton();
      }
    
    public void crearBoton(){
        JButton bVistaMenu=new JButton();
        bVistaMenu.setIcon(new ImageIcon("src/img/atras.png"));
        loc.fill= GridBagConstraints.NONE;
        loc.gridx = 0;
        loc.gridy = 2;
        loc.weighty = 0;
        loc.weightx = 0;
        loc.ipadx=100;
        loc.anchor=GridBagConstraints.LINE_START;
        loc.insets = new Insets(10,10,10,10);  
        loc.weightx=1.0;
        this.add(bVistaMenu, loc);
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
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon("src/img/fondo2.jpg").getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight()+5, this);
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
    