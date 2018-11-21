/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Guille
 */
public class VistaRegistro {
    private JPanel pVentana,pNombre,pAge,pFoto,pBoton,pImagenSelect;
    private Panel pImg1,pImg2,pImg3;
    private Vista vista;
    private JLabel titulo;
    private TextField tfName;
    private Choice chAge;
    private JLabel lFoto;
    private JButton boton;
    private ControladorRegistro controlador;
    private JLabel fImg1,fImg2,fImg3, fImg4;
    private ImageIcon img1,img2,img3,img4;

    private Border borde  = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4), BorderFactory.createLoweredBevelBorder());


    //la uso en 3 metodos, solo la genero una vez
    private Font nombres = new Font("Agency FB", Font.BOLD, 30);
    

    
    public VistaRegistro(JPanel pVentana, Vista vista, Logica logica) {
        this.pVentana = pVentana;
        this.vista = vista;
        pVentana.setLayout(new GridLayout(6, 0));
        controlador=new ControladorRegistro(this,logica);
        anadir();
    }
    private void anadir(){
        titulo();//anadimos el titulo
        pVentana.addMouseListener(controlador);
        pVentana.setName("panel");
        pVentana.add(titulo);
        
        //separador para que no se pegue al titulo
        JSeparator separador=new JSeparator();
        separador.setVisible(false);
        pVentana.add(separador);

        generarNombre();
        pVentana.add(pNombre);
        
        generarEdad();
        pVentana.add(pAge);
        
        generarImagen();
        pVentana.add(pFoto);
        
        generarBoton();
        pVentana.add(pBoton);
        
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
        boton.addMouseListener(controlador);
        boton.setName("siguiente");
        pBoton.add(boton, BorderLayout.EAST);
        
    }
    
    private void generarNombre(){
        pNombre=new JPanel();
        JLabel lName=new JLabel("Name -->",SwingConstants.CENTER);
        lName.setFont(nombres);
        pNombre.add(lName);
        
        tfName=new TextField("", 20);
        tfName.setName("name");
        tfName.addMouseListener(controlador);
        pNombre.add(tfName);
    }
    
    private void generarEdad(){
        pAge=new JPanel();
        JLabel lAge=new JLabel("AGE -->",SwingConstants.CENTER);
        lAge.setFont(nombres);
        pAge.add(lAge);
        chAge=new Choice();
        chAge.add(" ");
        for (int i = 1; i < 50; i++) {
            chAge.add(""+i);
        }
        chAge.select(0);
        
        chAge.addMouseListener(controlador);
        chAge.setName("age");
        pAge.add(chAge);
    }
    private void generarImagen(){
        pFoto=new JPanel();
        pImagenSelect=new JPanel();
        lFoto=new JLabel("FOTO -->",SwingConstants.CENTER);
        //lFoto.setFocusable(true);
        lFoto.setName("anadirFoto");
        
        lFoto.addMouseListener(controlador);//anadimos el escuchador
        lFoto.setFont(nombres);
        pFoto.add(lFoto);
        
        fImg1=new JLabel();
        fImg2=new JLabel();
        fImg3=new JLabel();
        fImg4=new JLabel();

        pImg1 =new Panel();
        //ImageIcon imagen = new ImageIcon(this.getClass().getResource("/fotos/1.jpg"));
        Image img;
        img=new ImageIcon(this.getClass().getResource("/fotos/1.jpg")).getImage();
        img1=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fImg1.setIcon(img1);
        fImg1.setName("imagen1");
        fImg1.addMouseListener(controlador);
        pFoto.add(fImg1);
        
        img=new ImageIcon(this.getClass().getResource("/fotos/2.jpg")).getImage();
         img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fImg2.setIcon(img2);
        
        fImg2.setName("imagen2");
        fImg2.addMouseListener(controlador);
        pFoto.add(fImg2);
        
        
        img=new ImageIcon(this.getClass().getResource("/fotos/3.jpg")).getImage();
        img3=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fImg3.setIcon(img3);
        
        fImg3.setName("imagen3");
        fImg3.addMouseListener(controlador);
        pFoto.add(fImg3);
        
        fImg4.setName("imagen4");
        fImg4.addMouseListener(controlador);
        pFoto.add(fImg4);
    }
    private void titulo(){
        titulo=new JLabel("<html><u>REGISTRO</u></html>!",SwingConstants.CENTER);
        Font fuente = new Font("Agency FB", Font.BOLD, 140);
        titulo.setFont(fuente);
    }
    
    
    /////////////////////////////////
    public void deseleccionarImagenes(){
        fImg1.setBorder(null);
        fImg2.setBorder(null);
        fImg3.setBorder(null);
        fImg4.setBorder(null);
    }
    public void seleccionarImagenes(int imagen){
        switch (imagen) {
            case 1:
                fImg1.setBorder(borde);
                break;
            case 2:
                fImg2.setBorder(borde);
                break;
            case 3:
                fImg3.setBorder(borde);
                break;
            case 4:
                fImg4.setBorder(borde);
                break;
            default:
                System.out.println("error en seleccionar imaegen "+imagen);
        }
    }
    public int imagenSeleccioanda(){
        if(fImg1.getBorder()!=null)
            return 1;
        if(fImg2.getBorder()!=null)
            return 2;
        if(fImg3.getBorder()!=null)
            return 3;
        if(fImg4.getBorder()!=null)
            return 4;
        return 0;
    }
    public int anadirImagen(String ruta){
        ImageIcon img = new ImageIcon(ruta); 
        img4 = new ImageIcon(img.getImage().getScaledInstance(100, 100,  Image.SCALE_SMOOTH)); 
        fImg4.setIcon(img4);
        return 4;//nº de umagen
    }
    public void estadoBoton(boolean estado){
        boton.setEnabled(estado);
    }
    public String datos(String dato){
        switch (dato.toLowerCase()) {
            case "name":
                return tfName.getText();
            case "age":
                return chAge.getSelectedItem();
            case "foto":
                return ""+imagenSeleccioanda();
            default:
                throw new AssertionError();
        }
    }
    public ImageIcon getImagen(int imagen){
        switch (imagen) {
            case 1:
                return img1;
            case 2:
                return img2;
            case 3:
                return img3;
            case 4:
                return img4;
            default:
                System.out.println("error al elegir imagen");;
        }
        return img1;
    }
    public void crearApp(){
        
        vista.app();
    }
    
    
}
