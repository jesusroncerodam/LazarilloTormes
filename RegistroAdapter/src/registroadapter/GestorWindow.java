/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Guille
 */
public class GestorWindow implements WindowListener {
    
    private final int MAXIMOVENTANA=100;
    public GestorWindow() {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        int x=e.getWindow().getLocation().x;
        int y=e.getWindow().getLocation().y;
        int numAleatorioX=(int) (Math.random() * MAXIMOVENTANA) + 1;
        int numAleatorioY=(int) (Math.random() * MAXIMOVENTANA) + 1;

        if(((int) (Math.random() * 2) + 1)%2==0){
            x+=numAleatorioX;
        }else{
            x+=numAleatorioX;
        }
        if(((int) (Math.random() * 2) + 1)%2==0){
            y+=numAleatorioY;
        }else{
            y-=numAleatorioY;
        }
        
        e.getWindow().setLocation(x, y);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
}
