package creacionvistas;


import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextField;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author JesusClase
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

    JLabel labelNombre, labelTema, labelDificultad;
    TextField nombre;
    JPanel tema1, tema2, tema3;
    JButton atras, adelante;

    private ImageIcon imagenFlecha = new ImageIcon("src/imagenes/flecha.png");
    private ImageIcon fondo = new ImageIcon("src/imagenes/fondo.jpg");
    Checkbox facil, medio, dificil;


    public void crearElementos() {
        panel1 = new JPanel();
        tema1 = new JPanel();
        tema2 = new JPanel();
        tema3 = new JPanel();

        labelNombre = new JLabel("Nombre: ");

        nombre = new TextField("Nombre");

        labelTema = new JLabel("Tema");
        labelDificultad = new JLabel("Dificultad");
        CheckboxGroup cbg = new CheckboxGroup();

        facil = new Checkbox("Facil", cbg, true);
        medio = new Checkbox("Medio", cbg, false);
        dificil = new Checkbox("Dificil", cbg, false);

        adelante = new JButton();
    }


    /**
     * Añade los elementos a la vista
     */
    public void anadirElementos() {
//        add(panel1);

        add(labelNombre);
        add(nombre);

        add(labelTema);
        add(tema1);
        add(tema2);
        add(tema3);

        add(labelDificultad);
        add(facil);
        add(medio);
        add(dificil);

        add(adelante);
    }


    /**
     * Asigno las posiciones a los elementos
     */
    public void posicionElementos() {
        panel1.setBounds(0, 0, ANCHOMARCO - 6, ALTOMARCO - 29);

        labelNombre.setBounds(15, 15, 100, 30);
        nombre.setBounds(120, 15, 100, 30);

        labelTema.setBounds(15, 100, 100, 30);
        tema1.setBounds(100, 100, 75, 75);
        tema2.setBounds(200, 100, 75, 75);
        tema3.setBounds(300, 100, 75, 75);

        labelDificultad.setBounds(15, 230, 75, 30);
        facil.setBounds(100, 240, 75, 20);
        medio.setBounds(200, 240, 75, 20);
        dificil.setBounds(300, 240, 75, 20);

        adelante.setBounds(250, 350, 100, 50);

        atras = new JButton();
        add(atras);
        atras.setBounds(50, 350, 100, 50);

    }


    /**
     * Le asigno el estilo de letra y demás pijadas a los objetos
     */
    public void estiloElementos() {
        panel1.setBackground(Color.gray);

        tema1.setBackground(Color.red);
        tema2.setBackground(Color.darkGray);
        tema3.setBackground(Color.green);

        atras.setIcon(cambiarTamano(imagenFlecha, atras.getWidth(), atras.getHeight()));
        adelante.setIcon(cambiarTamano(fondo, adelante.getWidth(), adelante.getHeight()));
    }


    /**
     * Le asigna la forma al marco
     */
    public void formaMarco() {
        setLayout(null);
        setResizable(false);
        setBounds(550, 150, ANCHOMARCO, ALTOMARCO);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Añade y crea el controlador de la vista
     */
    public void anadirControladores() {
        panel1.setName("panelMarco1");
    }


    /**
     * Modifica el tamaño de las imagenes
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        Image imagen = icono.getImage();
        Image reescalada = imagen.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        icono = new ImageIcon(reescalada);
        return icono;
    }


    /**
     * Modifica el tamaño de las imagenes
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public Image cambiarTamano(Image icono, int anchoImagen, int altoImagen) {
        Image reescalada = icono.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
        return reescalada;
    }
}
