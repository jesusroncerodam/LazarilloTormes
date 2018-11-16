package creacionvistas;


import java.awt.Color;
import java.awt.TextField;
import java.util.Random;
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
        
        flechaAtrás = new JButton();
        avanzarPagina = new JButton();
        

    }


    /**
     Añade los elementos a la vista
     */
    public void anadirElementos() {
        add(panel1);

    }


    /**
     Asigno las posiciones a los elementos
     */
    public void posicionElementos() {
        panel1.setLayout(null);
        panel1.setBounds(0, 0, ANCHOMARCO - 6, ALTOMARCO - 29);

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
        //setIconImage(imagenFlecha.getImage());
        //setTitle("Titulo 1");
        setLayout(null);
        setResizable(false);
        setBounds(550, 150, ANCHOMARCO, ALTOMARCO);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     Añade y crea el controlador de la vista
     */
    public void anadirControladores() {
        panel1.setName("panelMarco1");

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
}
