/**
 * JLabel que se muesta , cada carta, gestina cuando se muee 
 */
package vista;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.Border;


/**

 @author Guille
 */
public class Carta extends JLabel {

    private ImageIcon imgCarta, imgVuelta;
    private String url;
    private final Border borde = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1), BorderFactory.createLoweredBevelBorder());
    private int altura, ancho;
    private boolean sale;
    private Timer timer;
    private boolean bloquear;
    
    private final String VUELTA_IMAGEN="src/img/cartas/imgVuelta.png";
    private final int WIDTH_DEF, HEIHT_DEF,TIEMPO_ANIMACION=2;


    /**
     * Constructor de la Carta se le manda la localizacion de la carta
     * @param url 
     */
    public Carta(String url) {
        this.setBorder(borde);//ponemos un borde
        imgVuelta = new ImageIcon("src/img/cartas/vuelta.png");//("src/img/cartas/vuelta.png");////////////////////////////////////////////////////////ERROR SI SE LE PONE LA LOICALIZACION POR VARIABLE NO VA
        this.setSize(imgVuelta.getIconWidth(),imgVuelta.getIconHeight());//la imagen de "imgVuelta" da las dimensiones
        this.setIcon(imgVuelta);//asignamos como icono la imagen de imgVuelta
        imgCarta = cambiarTamano(new ImageIcon(url), imgVuelta.getIconWidth(), imgVuelta.getIconHeight());//asignamos un tamaño a la carta, para que esta se vea con las mismas 
        
        //asignamios datos
        this.url = url;
        bloquear=false;
        sale=false;
        WIDTH_DEF=imgVuelta.getIconWidth();//debe de estar despues de asignarle la imagen a imgVuelta
        HEIHT_DEF=imgVuelta.getIconHeight();//debe de estar despues de asignarle la imagen a imgVuelt
    }

    /**
     * Pintamos el fondo , el fondo siempre estará pero dentra una imagen encima 
     * o no , dependiendo del estado en el que se encuentre
     * @param g 
     */
    public void paint(Graphics g) {
        //imagen de fondo, la carta
        g.drawImage(imgCarta.getImage(), 0, 0, null);
        super.paint(g);
    }
    
    /**
     * Animamos la imagen, ya sea la anumacion de entrada o de salida, esto lo 
     * controla la propia carta, anima simpre que se pueda, si la carta esta 
     * bloqueada no va a animar
     */
    public void animar(){
        if(!bloquear){
            if(!sale){
                animarSalir();
            }else{
                animarEntrar();
            }
        }
    }
    
    /**
     *  Comienza la animacion de salida, la imagen desaparece poco a poco
     *  coge las dimensiones de la uimagen de vuelta y las almacena para ir
     *  reduciendo poco a poco su tamaño
     */
    private void animarSalir() {
        altura = imgVuelta.getIconHeight();
        ancho = imgVuelta.getIconWidth();
        sale = true;
        empezar();
    }

    /**
     * Comienza la animacion de entrar, la imagen va apareciendo poco a poco
     * se asigna un icono para que lo aumente con un tamaño minimo puesto que 
     * si esta en animarEntrar antes a hecho la animoacion de salir y la imagen 
     * sería null
     */
    private void animarEntrar() {
        this.setIcon(cambiarTamano((ImageIcon) imgVuelta, 2, 2));
        sale = false;
        empezar();
    }

    /**
     * Casigna el timer , si hay una animacion en progreso, la pausa y crea una 
     * Esta hecho para que no entre en bucle cuando haces click en la isma imagen
     * mas de una vez mientras estaba apareciendo; Cuando asigna el timer dependiendo
     * de el estado de sale, si esta en animacion entrar o anumacion salir, aumentara o
     * disminuira el tamano de la imagen
     */
    private void empezar(){
        if (timer != null){//si existe un timer lo paramos, evitamos tener 2 timer,
                            //uno aumentando el tamano y otro restando el tamano
            timer.stop();
        }
        
        timer = new Timer(TIEMPO_ANIMACION, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(sale){
                    altura--;
                    ancho--;
                }else{
                    altura++;
                    ancho++;
                }
                ponerImagen();
            }
        });
        timer.start();
        timer.setRepeats(true);
    }


    
    private void ponerImagen() {
        if(altura>0 && ancho>0 && altura<imgVuelta.getIconHeight() && ancho<imgVuelta.getIconWidth()){
            this.setIcon(cambiarTamano(new ImageIcon("src/img/cartas/vuelta.png"), ancho, altura));//"src/img/cartas/vuelta.png"
        }else{
            if (sale) {
                setIcon(null);
            } else {
                setIcon(imgVuelta);
            }
            timer.stop();
        }
        this.updateUI();
    }

    public boolean isSale() {
        if(bloquear){//bloquear variable que desactiva el giro de la carta de nuevo 
            return false;
        }
        return sale;//=true si no esta
    }

    public void bloquear() {
        bloquear=true;
        repaint();
        this.setIcon(imgCarta);//cambiarTamano(imgCarta,WIDTH_DEF,HEIHT_DEF));
    }

    public String getUrl() {
        return url;
    }
    
    
    /**
     Modifica el tamaño de las imagenes

     @param icono       se pasa por parametro el icono a reescalar
     @param anchoImagen establece el ancho de la imagen
     @param altoImagen  establece el alto de la imagen
     @return devuelve un ImageIcon que se asigna ya reescalado al original
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }
}
