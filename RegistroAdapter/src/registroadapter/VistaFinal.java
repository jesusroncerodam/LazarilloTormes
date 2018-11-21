/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

/**
 *
 * @author Guille
 */
public class VistaFinal { 
    private JPanel pVentana,pFinal;
        private Vista vista;

    private Border borde  = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4), BorderFactory.createLoweredBevelBorder());
    private Font nombres = new Font("Agency FB", Font.BOLD, 60);

    public VistaFinal(JPanel pVentana) {
        this.pVentana = pVentana;
        pVentana.setLayout(null);
        
                
        JLabel fImg1=new JLabel();
        JLabel texto=new JLabel("FELICIDADES!");
        texto.setFont(nombres);
        
        Image img;
        img=new ImageIcon(this.getClass().getResource("/fotos/1.jpg")).getImage();
        ImageIcon img1=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        fImg1.setIcon(img1);
        //fImg1.setName("FELICIDADES!");
       // pVentana.setBorder(borde);
       
        pFinal=new JPanel(new GridLayout(3, 2));
        pFinal.setBorder(borde);
        pFinal.setBounds(300, 150, 300, 300);

        
        this.pFinal.add(fImg1);
        this.pFinal.add(texto);
        this.pVentana.add(pFinal);
    }
    
   
}
