package creacionvistas;


import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**

 @author JesusClase
 */
public class VistaIntroduccionDatos extends JFrame {

    private JFrame marco1;

    private JPanel panel1;
    private final int ANCHOMARCO = 400, ALTOMARCO = 500;
    private JLabel nickname;


    public VistaIntroduccionDatos() {
        this.marco1 = this;
        crearVistaIntroduccionDatos();
    }


    public void crearVistaIntroduccionDatos() {
        crearElementos();
        anadirElementos();
        posicionElementos();
        estiloElementos();
        formaMarco();
        anadirControladores();
    }


    public void crearElementos() {
        panel1 = new JPanel();

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
