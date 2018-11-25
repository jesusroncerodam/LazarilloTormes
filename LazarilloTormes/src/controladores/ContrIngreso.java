/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import vista.VIngreso;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class ContrIngreso extends MouseAdapter implements ItemListener {

    private VIngreso vistaIngreso;
    private Logica logica;


    public ContrIngreso(VIngreso vista, Logica logica) {
        this.vistaIngreso = vista;
        this.logica = logica;
    }


    /**
     * Llama al metodo alojado en logica utilizado como un constructor para
     * igualar el Controlador local con el original
     */
    public void mandarControlador() {
        logica.asignarControladorIngreso(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        logica.vistaIngresoMouseListener(e);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        logica.vistaIngresoItemChange(e);
    }


    /**
     * Metodo puente para asignar los bordes a los iconos de los avatares
     * @param avatar int que se utiliza en el case para asignar el borde
     *               dependiendo de que icono esté seleccionado
     */
    public void asignarBordeAvatar(int avatar) {
        vistaIngreso.asignarBordeAvatar(avatar);
    }


    /**
     * Metodo puente para asignar los bordes a los iconos de los temas
     * @param tema int que se utiliza en el case para asignar el borde
     *             dependiendo de que icono esté seleccionado
     */
    public void asignarBordeTema(int tema) {
        vistaIngreso.asignarBordeTema(tema);
    }


    /**
     * Metodo puente para asignar los bordes a los iconos de las dificultades
     * @param dificultad int que se utiliza en el case para asignar el borde
     *                   dependiendo de que icono esté seleccionado
     */
    public void asignarBordeDificultad(int dificultad) {
        vistaIngreso.asignarBordeDificultad(dificultad);
    }


    /**
     * Metodo utilizado para llamar al metodo encargado de cambiar de vista
     * @param vistaDestino String con el nombre de la vista a la que vamos a
     *                     cambiar
     */
    public void cambiarVista(String vistaDestino) {
        vistaIngreso.cambiarDeVista(vistaDestino);
    }


    /**
     * Metodo que llama a otro metodo encargado de recoger los datos de nombre,
     * avatar elegido, tema elegido, y dificultad elegido
     */
    public void mandarRecogerDatos() {
        vistaIngreso.mandarDatos();
    }


    /**
     * Metodo encargado de gestionar los datos que se recogen de la
     * vista de Ingreso
     * @param avatar     String ruta del avatar
     * @param tema       int 1,2 o 3, tema de la partida, de las cartas
     * @param dificultad int 1,2 o 3 dificultad, cantidad de cartas a mostrar
     * @param nombre     String nombre del jugador
     */
    public void mandarDatos(String avatar, int tema, int dificultad, String nombre) {
        logica.recogerDatos(avatar, tema, dificultad, nombre);
    }


    /**
     * Este método es llamado desde la lógica para pasar el string con el path
     * para crear la imagen elegida por el usuario
     * @param imagenElegida String con el path de la imagen elegida
     */
    public void establecerImagenElegida(String imagenElegida) {
        vistaIngreso.establecerImagenElegida(imagenElegida);
    }
}
