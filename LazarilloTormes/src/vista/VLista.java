package vista;


import controladores.ContrLista;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import lazarilloTormes.Logica;
import lazarilloTormes.Vista;


/**
 *
 * @author Guillermo Manso
 * @author Jesus Roncero
 */
public class VLista extends JPanel {

    private final String[] NOMBRE_COLUMNAS = {"Nº", "Image", "Name", "Mov.", "Time"};
    private final Color COLOR_LETRAS = Color.white;
    private String[] datos;
    private Vista vistaMain;
    private ContrLista controlador;
    private GridBagConstraints loc;
    private JPanel lista;
    private final int NUMERO_MINIMO_FILAS = 15;

    /**
    * Constructor, solo se asigna la logica y vista, el resto se genera 
    * llamando al metodo generar
    * @param logica logica 
    * @param vista vista a la que refiere(padre)
    */
    public VLista(Logica logica, Vista vista) {
        this.vistaMain = vista;
        controlador = new ContrLista(this, logica);
    }


    /**
     * Generamos la vista, creando una tabla con un JScrollPane
     */
    public void generar() {
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        loc = new GridBagConstraints();
        loc.weighty = 0.5;
        loc.anchor = GridBagConstraints.CENTER;
        this.setBackground(Color.blue);
        recogerDatos();
        generarLista();
        crearBoton();
    }


    /**
     * Creamos la lista con todos sus componentes
     */
    public void generarLista() {
        lista = new JPanel(new GridLayout(obtenerColumnas(), 1, 0, 0));
        lista.setOpaque(false);
        String[] elementos;

        generarPrimeraFila();

        for (int i = 0; i < datos.length; i++) {
            JPanel fila = new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length));
            elementos = datos[i].split(";");
            //si es par ponemos un color y si es inpar otro
            if (i % 2 != 0) {
                fila.setBackground(new Color(255, 255, 255, 200));
            } else {
                fila.setBackground(new Color(99, 132, 252, 200));
            }

            fila.add(new JLabel("" + (i + 1), SwingConstants.RIGHT));//añadimos la posicion
            for (int j = 0; j < elementos.length; j++) {//movimientos0;tiempo1;imagen2;nombre3
                switch (j) {//j seria la posicionde la lista 
                    case 0://imagen, en el array es la pos 2
                        if (elementos[2] == null || (elementos[2].indexOf('\\') != -1 && !new File(elementos[2]).exists())) {//si el elemento es nulo o tiene un ruta absoluta y si el elemento existe
                            System.out.println("El elemento '" + elementos[2] + "' es nulo o tiene una ruta absoluta y no existe");
                            elementos[2] = "/img/avatar1.jpg";
                        }
                        if (elementos[2].indexOf('\\') != -1) {
                            fila.add(new JLabel(cambiarTamano(new ImageIcon(elementos[2]), 20, 20), SwingConstants.CENTER));
                        } else {
                            fila.add(new JLabel(cambiarTamano(new ImageIcon(this.getClass().getResource(elementos[2])), 20, 20), SwingConstants.CENTER));
                        }
                        break;

                    case 1://nombre, en el array es la pos 3
                        fila.add(new JLabel(elementos[3]));
                        break;

                    case 2://movimientos, en el array es la pos 0
                        fila.add(new JLabel(elementos[0]));
                        break;

                    case 3://tiempo, en el array es la pos 1
                        fila.add(new JLabel(elementos[1]));
                        break;

                    default:
                        System.out.println("error " + j);
                }
            }
            lista.add(fila);
        }
        rellenar();
        anadirScroll();
    }


    /**
     * Retornamos el numero de columnas que tendrá la el JPanel
     * @return int, columnas
     */
    private int obtenerColumnas() {
        int columnas = datos.length + 1;//+1 ya que en datos no esta la cabecera
        if (datos.length < NUMERO_MINIMO_FILAS + 1) {
            columnas = NUMERO_MINIMO_FILAS + 1;
        }
        return columnas;
    }


    /**
     * En caso de que no tengamos un gran numero de estadisticas, para que el
     * texto no se descompense, agregamos unas filas vacias
     */
    private void rellenar() {
        for (int i = datos.length; i < NUMERO_MINIMO_FILAS; i++) {
            JPanel fila = new JPanel(new GridLayout(1, 1));
            if (i % 2 != 0) {
                fila.setBackground(new Color(255, 255, 255, 100));
            } else {
                fila.setBackground(new Color(99, 132, 252, 100));
            }
            lista.add(fila);
        }
    }


    /**
     * Creamos el JSrollPane respecto al JPanel lista, adjuntando a una posicion
     */
    private void anadirScroll() {
        JScrollPane scroll = new JScrollPane(lista, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 30), COLOR_LETRAS));
        scroll.setPreferredSize(new Dimension(500, 500));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        loc.fill = GridBagConstraints.BOTH;
        loc.gridx = 0;
        loc.gridy = 0;
        loc.weighty = 2;
        loc.weightx = 2;
        loc.insets = new Insets(50, 25, 25, 25);
        this.add(scroll, loc);
    }


    /**
     * Creamos la primera fila, la que mostrara el array NOMBRE_COLUMNAS
     */
    private void generarPrimeraFila() {
        JPanel primeraFila = new JPanel(new GridLayout(1, NOMBRE_COLUMNAS.length));
        for (int i = 0; i < NOMBRE_COLUMNAS.length; i++) {
            JLabel dato = new JLabel(NOMBRE_COLUMNAS[i], SwingConstants.LEFT);
            dato.setForeground(COLOR_LETRAS);

            switch (i) {//en funcion de el valor lo ajustamos a una alineaccion
                case 0:
                    dato.setHorizontalAlignment(SwingConstants.RIGHT);
                    break;
                case 1:
                    dato.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                case 4:
                    dato.setText("");
                    dato.setIcon((cambiarTamano(new ImageIcon(this.getClass().getResource("/img/relojWh.png")), 20, 20)));
                    break;
            }
            primeraFila.add(dato);
        }
        primeraFila.setBackground(new Color(47, 92, 255, 200));//fondo del panel con algo de transparencia
        lista.add(primeraFila);
    }


    /**
     * Crea el boton de vuelta a la vista principal
     */
    private void crearBoton() {
        JButton bAtras = new JButton(new ImageIcon(this.getClass().getResource("/img/atras.png")));
        bAtras.setContentAreaFilled(false);
        bAtras.setBorder(null);
        bAtras.setToolTipText("Go to main");

        bAtras.addMouseListener(controlador);

        loc.fill = GridBagConstraints.NONE;
        loc.gridx = 0;
        loc.gridy = 2;
        loc.weighty = 0;
        loc.weightx = 0;
        loc.ipadx = 50;
        loc.anchor = GridBagConstraints.LINE_START;
        loc.insets = new Insets(10, 10, 10, 10);
        loc.weightx = 1.0;
        this.add(bAtras, loc);
    }


    /**
     * Recoge las estadisticas almacenadas en el fichero
     */
    public void recogerDatos() {
        datos = controlador.datosFichero();
    }


    /**
     * Modifica el tamaño de los iconos
     * @param icono       objeto tipo ImageIcon que se pasa para cambiarle el
     *                    tamaño
     * @param anchoImagen int que indica el nuevo ancho
     * @param altoImagen  int que indica el nuevo alto
     * @return ImageIcon que se igual al original para actualizar el tamaño
     */
    private ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        return new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon(this.getClass().getResource("/img/fondoEstadisticas.jpg")).getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight() + 5, this);
    }


    /**
     * Es el metodo que se encarga de vincular la vista principal con el resto,
     * es comun en todas las vistas
     *
     * le manda un string que viene de la logica e indica a que vista nos
     * estamos moviendo
     * @param vista String, es el nombre de la vista a la que vamos a cambiar
     */
    public void cambiarVista(String vista) {
        vistaMain.cambiarVista(vista);
    }
}
