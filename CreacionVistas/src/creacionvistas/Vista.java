package creacionvistas;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextField;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**

 @author JesusClase
 */
public class Vista extends JFrame {

    private JFrame marco1;
    ControladorVista controladorVista;

    private JPanel panel1;
    private final int ANCHOMARCO = 500, ALTOMARCO = 500;
    private JLabel labelNombre, labelApodo;
    private TextField campoNombre;
    private JButton flechaAtrás, avanzarPagina;
    private GridBagConstraints constrain;

    ImageIcon imagenFlecha = new ImageIcon("src/img/flecha.png");


    public Vista() {
        this.marco1 = this;
        crearVista();
    }


    public void crearVista() {
        crearElementos();
        anadirElementos();
        posicionElementos();
        estiloElementos();
        formaMarco();
        anadirControladores();
    }


    public void crearElementos() {
        panel1 = new JPanel();

        labelNombre = new JLabel();
        labelApodo = new JLabel();
        campoNombre = new TextField();

        flechaAtrás = new JButton(imagenFlecha);
        avanzarPagina = new JButton();

        constrain.gridx = 2;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
    }


    /**
     Añade los elementos a la vista
     */
    public void anadirElementos() {
        add(panel1);

        panel1.add(labelNombre, constrain);
        panel1.add(labelApodo);
        panel1.add(campoNombre);
        panel1.add(flechaAtrás);
        panel1.add(avanzarPagina);
    }


    /**
     Asigno las posiciones a los elementos
     */
    public void posicionElementos() {

    }


    /**
     Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {
        panel1.setBackground(colorAleatorio());
    }


    /**
     Le asigna la forma al marco
     */
    public void formaMarco() {
        setLayout(new GridBagLayout());
        setResizable(false);
        setBounds(550, 150, ANCHOMARCO, ALTOMARCO);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     Añade y crea el controlador de la vista
     */
    public void anadirControladores() {

        controladorVista = new ControladorVista(this);
    }


    /**
     Proporciona un color aleatorio para el panel
     @return Devuelve un color
     */
    public Color colorAleatorio() {
        Random aleatorio = new Random();
        int red = aleatorio.nextInt(255);
        int green = aleatorio.nextInt(255);
        int blue = aleatorio.nextInt(255);
        Color color = new Color(red, green, blue);
        return color;
    }


    /**
     Modifica el tamaño de los iconos

     @param icono
     @param anchoImagen
     @param altoImagen
     @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        return new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH));
    }


    /**
     Modifica el tamaño de las imagenes

     @param icono
     @param anchoImagen
     @param altoImagen
     @return
     */
    public Image cambiarTamano(Image icono, int anchoImagen, int altoImagen) {
        return icono.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
    }

}
