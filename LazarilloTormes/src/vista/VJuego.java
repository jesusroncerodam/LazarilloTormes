/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import controladores.ContrJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import trabajodi.Logica;


/**
 *
 * @author Guille
 */
public class VJuego extends JPanel {

    private Font fuente = new Font("Agency FB", Font.BOLD, 40);
    private final int HGAP = 20, VGAP = 5;
    private ContrJuego controlador;
    private ArrayList<Carta> carta;
    private int contadorSeg, contMov, desactivadas;
    private Timer tReloj;
    private JLabel lMovimientos, lReloj;
    private JButton bPausaPlay, bGuardar, bContinuar;
    private GridBagConstraints constrain;
    
    private boolean defecto;


    /**
     * Constructor, solo se asigna la logica, el resto se genera llamando al
     * metodo
     * generar
     * @param logica
     */
    public VJuego(Logica logica) {
        controlador = new ContrJuego(this, logica);
    }

    /**
     * Encargado de generar un juego nuevo, inicialiA todas las variables y
     * controladores
     * @param rutas Array de String, direccion de todas las imagenes de cartas
     */
    public void generar(boolean defecto){
        this.defecto=true;
        controlador.asignarControlador();//asignamos el controlador a la logica
        desactivadas = 0;

        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(controlador);

        constrain = new GridBagConstraints();
        constrain.weighty = 0.5; //para que se estiren las columnas
        // constrain.weightx=1;
        //  constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());

        //asignamos los labels de tiempo y movimientos
        asignarLabels();

        //generamos los botones
        playPause();
        guardar();
        continuar();

        //generamos las cartas
        generarCartas();
        guardarUrlCarta();
    }
    private String[] obtenerRutas(){
        return controlador.obtenerRutas();
    }


    /**
     * Asignamos los labels, tanto el de movimientos como el temporizador
     */
    private void asignarLabels() {
        //contador de movimientos
        contMov = 0;
        lMovimientos = new JLabel("Movimientos: " + contMov);
        lMovimientos.setFont(fuente);
        //lMovimientos.setForeground(new Color(74, 110, 242));///////////////////////////////////////////////////////////////////////////////////////////////////////////CAMBIAR COLOR, PONER VARIABLE
        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.fill = GridBagConstraints.BOTH;
        this.add(lMovimientos, constrain);

        //contador segundos
        contadorSeg = 0;
        ImageIcon imgReloj = new ImageIcon("src/img/reloj.png");
        lReloj = new JLabel("00:" + contadorSeg, imgReloj, JLabel.CENTER);
        System.out.println(imgReloj.getIconWidth());
        lReloj.setIconTextGap((int) (-imgReloj.getIconWidth() / 1.6));//si no hacemos esto, el texto saldría a la derecha de la imagen, no encima
        lReloj.setFont(fuente);
        lReloj.setForeground(new Color(74, 110, 242));///////////////////////////////////////////////////////////////////////////////////////////////////////////CAMBIAR COLOR, PONER VARIABLE
        constrain.gridx = 2;
        constrain.gridy = 0;
        this.add(lReloj, constrain);
        constrain.fill = GridBagConstraints.NONE;

    }


    /**
     * Creamos, asignamos y mostramos las images
     * @param rutas Array de string que cotiene las rutas de las imagenes
     */
    private void generarCartas() {
        String[] rutas=obtenerRutas();
        carta = new ArrayList();

        for (int i = 0; i < rutas.length; i++) { //asignamos la misma ruta a 2 carta
            carta.add(new Carta(rutas[i]));
            carta.add(new Carta(rutas[i]));
        }

        JPanel cartas = new JPanel();//creamos el panel donde estarán las cartas
        cartas.setOpaque(false);//fondo transparente para que se vea el fondo

        int cuadrado = (int) Math.sqrt(carta.size());//la raiz da lugar a unas fulas respectivas 
        cartas.setLayout(new GridLayout(cuadrado, cuadrado, HGAP, VGAP));//asignamos un layout a las cartas
        
        if(defecto)
            Collections.shuffle(carta);//deshordenamos las cartas asi estan colocadas de manera aleatoria

        for (int i = 0; i < carta.size(); i++) {//asignamos el indice al nombre de las cartas
            carta.get(i).setName("" + i);
        }

        for (int i = 0; i < carta.size(); i++) {//anadimos todas las cartas y les ponemos escuchador
            cartas.add(carta.get(i));
            carta.get(i).addMouseListener(controlador);
        }
        constrain.gridx = 0; // El área de texto empieza en la columna 0.
        constrain.gridy = 1; // El área de texto empieza en la fila 1
        constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
        constrain.gridheight = 1; // El área de texto ocupa 1 filas.
        constrain.fill = GridBagConstraints.NONE; //para que no se expanda
        constrain.weighty = 0.0;//`no deje espacio en el eje Y
        this.add(cartas, constrain);
    }


    /**
     * Encargado de iniciar el contador
     */
    private void empezarReloj() {
        tReloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contadorSeg++;
                int minutos = contadorSeg/60;
                int seg = contadorSeg%60;
                String tiempo = "00:"+contadorSeg;
                if(minutos<10){
                    tiempo = "0"+minutos+":";
                }else{
                    tiempo = minutos+":";
                }
                if(seg < 10){
                    tiempo +="0"+seg;
                }else{
                    tiempo +=seg;
                }
                lReloj.setText(tiempo);
                System.out.println("hoildsjf"+tiempo);
                repaint();
            }
        });
    }


    /**
     * Encargado de generar y asignar el JButon de PlayYpause
     */
    private void playPause() {
        bPausaPlay = new JButton(new ImageIcon("src/img/playPause.png"));
        bPausaPlay.setContentAreaFilled(false);
        bPausaPlay.setBorder(null);
        constrain.gridx = 0;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bPausaPlay, constrain);

        bPausaPlay.setActionCommand("playPause");
        bPausaPlay.addKeyListener(controlador);
        bPausaPlay.addMouseListener(controlador);
    }


    /**
     * Encargado de generar y asignar el JButon de continuar
     */
    private void continuar() {
        bContinuar = new JButton(new ImageIcon("src/img/flechaRect.png"));
        bContinuar.setContentAreaFilled(false);
        bContinuar.setBorder(null);
        constrain.gridx = 2;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bContinuar, constrain);
        cambiarEstadoBoton("continuar", true);
        bContinuar.setActionCommand("continuar");
        bContinuar.addKeyListener(controlador);
        bContinuar.addMouseListener(controlador);
    }


    /**
     * Encargado de generar y asignar el JButon de guardar
     */
    private void guardar() {
        bGuardar = new JButton(new ImageIcon("src/img/save.png"));
        bGuardar.setContentAreaFilled(false);
        bGuardar.setBorder(null);
        bGuardar.setActionCommand("guardar");
        constrain.gridx=1;
        constrain.gridy=2;
        constrain.fill= GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bGuardar, constrain);

        bGuardar.addKeyListener(controlador);
        bGuardar.addMouseListener(controlador);
    }
    /**
     * Pintamos el Fondo
     * @param g paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("src/img/fondo2.gif");
        g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight() + 5, this);
    }


    /**
     * Cambia el estado del boton, activado o desactivado
     *
     * @param boton  String, texto, corresponde al boton
     * @param estado booleano, pone el estado de este boton
     */
    public void cambiarEstadoBoton(String boton, boolean estado) {
        switch (boton) {
            case "continuar":
                bContinuar.setEnabled(!estado);
                break;
            case "guardar":
                bGuardar.setEnabled(!estado);
                break;
            case "playPause":
                bPausaPlay.setEnabled(!estado);
                break;
            default:
                System.out.println("error cambiarEstadoBoton" + boton);
        }
    }


    /**
     * Empieza crea o pausa el contador del timepo
     * @param accion String a realizar
     */
    public void gestionarContador(String accion) {
        if (tReloj == null) {
            empezarReloj();
        }
        switch (accion.toLowerCase()) {
            case "empezar":
            case "play":
            case "reanudar":
                tReloj.setRepeats(true);
                tReloj.start();
                break;

            case "pausa":
            case "pause":
                tReloj.stop();
                break;

            case "playpause":
                if (tReloj.isRunning()) {
                    tReloj.stop();
                } else {
                    tReloj.start();
                }
                tReloj.setRepeats(true);
                break;

            default:
                System.out.println("error gestionar contador" + accion.toLowerCase());
        }
    }


    /**
     * Retorna el entero que esta visible y sin bloquear, es decir el que este
     * sin
     * su pareja ya asignada, -1 si no encuentra a nadie
     * @return int valor de carta viible sin bloquear, -1 si no lo encuentra
     */
    public int algunaVisible() {
        for (int i = 0; i < carta.size(); i++) {
            if (carta.get(i).isSale()) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Anima la imagen la gira mostrando el fondo
     * @param i int, indice del arraylist de las cartas
     */
    public void girar(int i) {
        carta.get(i).animar();
    }


    /**
     * retorna si al imagen que ha hecho click apunta la imagen al la misma
     * imagrn (si tiene la misma ruta)
     * @param i indice de 1 carta a comparar
     * @param j indice de 2 carta a comparar
     * @return boolean, si son las mismsa imagen
     */
    public boolean mismaImagen(int i, int j) {
        return (carta.get(i).getUrl().equals(carta.get(j).getUrl()));
    }


    /**
     * bloquea pareja de imagenes para que no se puedan volver a girar y asi no
     * se muestren en al busar umagenes, tambien suma 2 a las cartas
     * descativadas
     * @param i indice del array de la carta a bloquear
     * @param j indice del array de la carta a bloquear
     */
    public void bloquearImagenes(int i, int j) {
        carta.get(i).bloquear();
        carta.get(j).bloquear();
        desactivadas += 2;
    }


    /**
     * Añade un movimiento al contador de movimientos
     */
    public void movimiento() {
        contMov++;
        lMovimientos.setText("Movimientos: " + contMov);
    }


    /**
     * retornas la partida finalizo
     * @return booleano, true si aun no termino
     */
    public boolean isFin() {
        return carta.size() > desactivadas;
    }


    public int getContadorSeg() {
        return contadorSeg;
    }


    public int getContMov() {
        return contMov;
    }


//    public void eliminarElementos() {
//        for (int i = 0; i < this.getComponentCount(); i++) {
//            remove(i);
//        }
//    }
    
    
    
    
    public ArrayList<String> guardarUrlCarta(){
        ArrayList<String> rutaGuardada=new ArrayList<String>();
        for (int i = 0; i < carta.size(); i++) {
            rutaGuardada.add(carta.get(i).getUrl());
            //System.out.println(i+" ->"+carta.get(i).getUrl());
        }
        return rutaGuardada;
    }
    public ArrayList<Boolean> guardarBloquearCarta(){
        ArrayList<Boolean> estadoCarta=new ArrayList<Boolean>();
        for (int i = 0; i < carta.size(); i++) {
            estadoCarta.add(carta.get(i).getBloquear());
            //System.out.println(i+" ->"+carta.get(i).getUrl());
        }
        return estadoCarta;
    }
    
    public void cargarBloquear(ArrayList<Boolean> bloqueadas){
        for (int i = 0; i < carta.size(); i++) {
            if(bloqueadas.get(i))//si bloquear es true lo bloqueamos
                carta.get(i).bloquear();
        }
    }
    public void sdjkb(){
        ArrayList<String> rutaGuardada=guardarUrlCarta();
        ArrayList<Boolean> estadoCarta=guardarBloquearCarta();
        int vuelta=algunaVisible();
        if(vuelta!=-1){//si hay alguna
            //lo guardamos
            
        }
            
     //   contadorSeg;
    }
    //guardar partida
    //datos:
    /*
    desactivadas
    contMov 
    contadorSeg
    
    */
    public void generarGuardada() {
        controlador.asignarControlador();//se tiene que volver a generar
        desactivadas = 0;

        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(controlador);

        constrain = new GridBagConstraints();
        constrain.weighty = 0.5; //para que se estiren las columnas
        // constrain.weightx=1;
        //  constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        this.setLayout(new GridBagLayout());

        //asignamos los labels de tiempo y movimientos
        asignarLabels();

        //generamos los botones
        playPause();
        guardar();
        continuar();

        //generamos las cartas
        generarCartas();
    }

}

/*
 * private void resize(){
 * this.addComponentListener(new java.awt.event.ComponentAdapter() {
 * public void componentResized(java.awt.event.ComponentEvent evt) {
 * System.out.println(evt.getComponent());
 * for (Carta carta1 : carta) {
 * System.out.println(getWidth()+"---"+getHeight());
 * carta1.actualizarTamaño();
 * }
 * }
 * });
 * }
 */
