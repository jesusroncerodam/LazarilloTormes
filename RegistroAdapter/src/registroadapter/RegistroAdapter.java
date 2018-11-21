/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

/**
 *
 * @author Guille
 */
public class RegistroAdapter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logica logica=new Logica();
        Vista vista=new Vista(logica);
    }
    
}
