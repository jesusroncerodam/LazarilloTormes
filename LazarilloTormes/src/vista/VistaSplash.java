/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import trabajodi.Vista;


/**

 @author Guille
 */
public class VistaSplash extends JPanel{

    private Vista vista;
    private final int TAM_WH_IMG = 200, TAM_HE_IMG = 200, MAX_WIDTH = 285, MAX_HEIGHT = 462;
    private Timer timerImg, timerBar;
    private int grados, valorP, cont, tam, tiempo;
    private Image imagen, imagenFondo;
    private JProgressBar progreso;
    private JLabel jTexto;
    private String[] textos;
    private Font font;

    /**
     Constructor Crea una vista splash
     se asignan 3 mensajes por defectos,
     extiende de JPanel
     @param rutaImg   ruta desde el main a la imagen que rota
     @param rutaFondo ruta desde el fondo a la imagen de fondo
     @param tiempo    tiempo hasta completar el progreso
     @param font      fuente de las letras
     */
    public VistaSplash(String rutaImg, String rutaFondo, int tiempo, Font font, Vista vista) {
        textos = new String[3];
        textos[0] = "Loading...";//"Cargando...";
        textos[1] = "Almost finished loading.";//"Casi hemos terminado";
        textos[2] = "Starting...";//"Iniciando...";  
        crearObjetos(rutaImg, rutaFondo, tiempo, font, vista);
    }


    /**
     Constructor Crea una vista splash
     extiende de JPanel
     @param rutaImg   ruta desde el main a la imagen que rota
     @param rutaFondo ruta desde el fondo a la imagen de fondo
     @param tiempo    en segundos, tiempo hasta completar el progreso
     @param font      fuente de las letras
     @param textos    array de String , contiene los mensajes de accion
     */
    public VistaSplash(String rutaImg, String rutaFondo, int tiempo, Font font, String[] textos, Vista vista) {
        this.textos = textos;
        crearObjetos(rutaImg, rutaFondo, tiempo, font, vista);
    }


    /**
     Metodo genera todos los objetos de la clase.
     evita reescribir el codigo
     @param rutaImg   ruta desde el main a la imagen que rota
     @param rutaFondo ruta desde el fondo a la imagen de fondo
     @param tiempo    en segundos, tiempo hasta completar el progreso
     @param font      fuente de las letras
     @param vista     vista padre
     */
    private void crearObjetos(String rutaImg, String rutaFondo, int tiempo, Font font, Vista vista) {
        this.vista = vista;
        this.font = font;
        setOpaque(false);//si no lo ponemos, no se ve el fondo
        this.tiempo = (tiempo * 1000) / 100;//pasamos a milisegundos y dividimos entre 100 para calcular cada cuanto se mueve el Jprogressbar
        cont = 0; //inicializamos los datos a "defecto" en el "constructor"
        grados = 0;
        tam = getWidth() + getHeight();
        imagen = new ImageIcon(this.getClass().getResource(rutaImg)).getImage();
        imagenFondo = new ImageIcon(this.getClass().getResource(rutaFondo)).getImage();
        this.setLayout(new GridLayout(3, 1));//asignamos el layout
        this.add(new JSeparator(SwingConstants.VERTICAL));//creamos un separador
        //gracias a el se separa del "top" y cuando cambia el tamaño , se agranda o empequeñece
        crearJlabel();
        progresBar();
    }


    /**
     Metodo encargado de crear, asignar y añadir el Jlabel
     */
    private void crearJlabel() {
        jTexto = new JLabel(textos[0], JLabel.CENTER);//asignamos el 1 texto y lo centramos
        jTexto.setForeground(new Color(230, 230, 230));//ponemos el fondo a las letras
        jTexto.setFont(font);//asignamos el fondo
        this.add(jTexto);
    }


    /**
     Metodo encargado de crear, asignar y añadir el Jprogressbar
     */
    private void progresBar() {
        progreso = new JProgressBar();//creamos el JProgress
        //progreso.setValue(0); //asignamos el valor inicialen 0 se asigna por defecto)
        progreso.setStringPainted(true);//mostramos el porgreso, el %
        progreso.setSize(500, 30); //colocamos el tamaño por defecto(despues se cambia con relacion/aspecto en un metodo
        progreso.setFont(font.deriveFont(15f));//asignamos  el tamaño por defecto(despues se cambia con relacion/aspecto en un metodo
        JPanel aux = new JPanel(null); //creaamos un JPanel , con layour null para colorcar el progress bar
        aux.setOpaque(false);//para que se vea el fondo de pantalla
        progreso.setLocation((700 - progreso.getWidth()) / 3, 0);//asignamos la localizacion, deja un "espacio" a la izquierda con relacion el tamaño del el Jprogress, y así lo deja a la derecha
        this.add(aux);//añadimos al JPanel de la clase
        aux.add(progreso);//añadimos el progreso al auxiliar
    }


    /**
     Colocamos los elementos en relacion/aspecto para que no se malformen
     */
    private void colocarElementos() {
        /*
         //
         //PONER DOS VARIABLES ANCH Y ALTO
         //
         */
        if (tam != getWidth() + getHeight()) {//cuando el anacho y alto no sea el guardado
            progreso.setSize((getWidth() * progreso.getWidth()) / ((progreso.getX() * 2) + progreso.getWidth()), getHeight() / 30);//realizamos reglas de 3
            progreso.setFont(font.deriveFont((float) (15 * getWidth()) / 580));//regla de 3 con respecto al tamaño de la letra del Jprogressbar
            jTexto.setFont(font.deriveFont((float) (font.getSize() * getWidth()) / 580));//regla de 3 con respecto al tamaño de la letra del JLabel
            //progreso.setLocation((getWidth()-progreso.getWidth())/3, 0);
            tam = getWidth() + getHeight();//guardamos el tamaño
        }
    }


    /**
     Sobrescribimos el metodo "paint" encargado de Pintar el fondo de pantalla y
     hacer girar la imagen
     @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        //colocamos la imagen desde el 0,0 hasta el tamañod e la imagen
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), null);//tenemos que estarlo pintando ya que el para hacer el efecto de rotacion esta imagen se elimina al rotar la otra
        //tambien dibujamos siempre ya que el tamaño (ancho y largo) puede cambiar
        int x = getWidth() / 2;//calculamos la mitad del acho
        int y = getHeight() / 4;//calculamos para lograr estar en el cuarto superior de la pantalla
        super.paint(g);//borramos la imagen anterior
        double rad = Math.toRadians(grados);//pasamos los grados a radianes, AffineTransform.rotate trabaja con radianes
        AffineTransform afineTr = new AffineTransform();//creamos el objeto que trata con estas transformaciones
        afineTr.rotate(rad, x, y);//rotamos la imagen, le mandamos el angulo y donde la va a colocar
        ((Graphics2D) g).setTransform(afineTr);//para pintar la imagen la transformamos Graphics a 2D
        //pintamos la imagen , mandamos la imagen, posicion y tamaño. 
        g.drawImage(imagen, x - (TAM_HE_IMG / 2), y - (TAM_WH_IMG / 2), TAM_WH_IMG, TAM_HE_IMG, null);//null ya que no necesitamos saber si la imagen a cambiado
        //super.paint(g);//posible easter egg, gira todo 
    }


    /**
     Metodo encargado de ir rellenndo el progreso, paso a paso y
     asignar los textos de progreso
     */
    private void rellenarProgreso() {
        if (valorP <= 100) {//mientras que el valor del progreso sea menor de 100
            if (cont < textos.length) {//mientras que el contador sea menor que el array de mensajes
                if (valorP % (100 / textos.length) == 0) {//esta operacion divide el progreso y muestra los mensajes en funcion de el tamaño del array de mensajes
                    jTexto.setText(textos[cont]);//asignamos el texto 
                    cont++;//sumamos al contador, (hemos pasado de mensaje)
                }
            }
            progreso.setValue(valorP); //asignamos el valor del del progressbar
            valorP += 1;//sumamos uno al progreso

        } else {//si ya ha llegado a 100 paramos la ejecucion, y llamamos a la vista avisandola de esto
            //paramos los contadores
            timerImg.stop();
            timerBar.stop();
            removeAll();
            //  notifyAll();
            vista.splashTermina();
        }
    }


    /**
     Encargado de crear y empezar la anumacion,
     empieza a girar la imagen y añade valores al contador
     */
    public void empezarAnimaciones() {
        timerImg = new Timer(tiempo / 5, new ActionListener() {//tiempo/5 para que sea respectivo junto al tiempo
            @Override
            public void actionPerformed(ActionEvent e) {
                grados = grados + 1;//añadimos un grado
                repaint();//pintamos la imagen
            }
        });

        timerBar = new Timer(tiempo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colocarElementos();//miramos si ha cambiado algun elemento antes de completar algun progreso
                rellenarProgreso();//rellenamos el progreso
            }
        });
        //empezamos los contadores
        timerBar.start();
        timerBar.setRepeats(true);
        timerImg.start();
        timerImg.setRepeats(true);
    }


    /**
     tamaño minimo para que la ventana se vea correctamente
     @return Dimension
     */
    public Dimension getMinimumSize() {
        return new Dimension(MAX_WIDTH, MAX_HEIGHT);
    }
}
