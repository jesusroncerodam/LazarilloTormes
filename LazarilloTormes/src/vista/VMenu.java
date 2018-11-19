/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrMenu;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VMenu extends MenuBar  {

    private ContrMenu controlador;


    public VMenu(Logica logica) {
        controlador = new ContrMenu(logica, this);
        generar();
    }

    public void generar(){
        
        MenuItem A1=new MenuItem("Salir",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('1'), false)));
        Menu menuV1=new Menu("Ajustes");
        menuV1.add(A1);
        
        
            MenuItem B1=new MenuItem("Sonido",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('1'), false)));
            MenuItem B2=new MenuItem("Atras",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('2'), true)));
            MenuItem C1=new MenuItem("Sonido",(new MenuShortcut(KeyEvent.getExtendedKeyCodeForChar('2'), false)));
            A1.addActionListener(controlador);
            A1.setActionCommand("Salir");
            //A2.addActionListener(controlador);
            //A2.setActionCommand("atras1");
            B1.addActionListener(controlador);
            B1.setActionCommand("alante2");
            B2.addActionListener(controlador);
            B2.setActionCommand("atras2");


           // menuV1.add(A2);

            Menu menuV2=new Menu("Vehiculo 2");
            menuV2.add(B1);
            menuV2.add(B2);

            MenuBar menuBar=new MenuBar();
            menuBar.add(menuV2);

            
        this.add(menuV1);
        
        //ventana.setMenuBar(menuBar);
    }
}
