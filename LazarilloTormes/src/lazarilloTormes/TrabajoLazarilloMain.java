package lazarilloTormes;

import java.util.Locale;


/**
 *
 * @author Guille
 */
public class TrabajoLazarilloMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);//Asignamos el lenguaje del programa a ingles para los dialogos modales
        Vista vista = new Vista(new Logica());//creamos la vista , mandandole la logica
    }
}
