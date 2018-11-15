/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package vista;


import controladores.ContrJuego;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import trabajodi.Logica;


/**

 @author Guille
 */
public class VJuego extends JPanel {

    private Font fuente = new Font("Agency FB", Font.BOLD, 40);

    private ContrJuego controlador;
    //private Carta[] carta;
    private ArrayList<Carta> carta;
    private int contadorSeg, contMov,desactivadas;
    private Timer tReloj;
    private JLabel lMovimientos, lReloj;
    private JButton bPausaPlay, bGuardar, bContinuar;
    private GridBagConstraints constrain;


    public VJuego(Logica logica) {
        controlador = new ContrJuego(this, logica);
    }


    public void generar(String[] rutas) {
        desactivadas=0;
        this.setOpaque(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(controlador);
        constrain = new GridBagConstraints();
        constrain.weighty = 0.5; //para que se estiren las columnas
        // constrain.weightx=1;
        //  constrain.fill = GridBagConstraints.BOTH;
        constrain.anchor = GridBagConstraints.CENTER;
        //resize();

        this.setLayout(new GridBagLayout());
        //generamos las cartas
        asignarLabels();
        //this.add(new Button("wsfjwenjgdbsjk"),BorderLayout.WEST);
        playPause();
        guardar();
        continuar();

        //  this.add(ba,BorderLayout.EAST);
        generarCartas(rutas);
    }


    private void asignarLabels() {
        contMov = 0;
        lMovimientos = new JLabel("Movimientos: " + contMov);
        lMovimientos.setFont(fuente);
        constrain.gridx = 0;
        constrain.gridy = 0;
        constrain.fill = GridBagConstraints.BOTH;
        this.add(lMovimientos, constrain);

        contadorSeg = 0;
        ImageIcon imgReloj = new ImageIcon("src/img/reloj.png");
        lReloj = new JLabel("" + contadorSeg, imgReloj, JLabel.CENTER);
        System.out.println(imgReloj.getIconWidth());
        lReloj.setIconTextGap((int) (-imgReloj.getIconWidth() / 1.6));
        lReloj.setFont(fuente);
        lReloj.setForeground(new Color(74, 110, 242));

        constrain.gridx = 2;
        constrain.gridy = 0;
        this.add(lReloj, constrain);
        constrain.fill = GridBagConstraints.NONE;

    }
 JPanel cartas ;

    private void generarCartas(String[] rutas) {
        int HGAP=20, VGAP=5;
        carta = new ArrayList();
        //asignamos la misma ruta a 2 carta
        for (int i = 0; i < rutas.length; i++) {
            carta.add(new Carta(rutas[i]));
            carta.add(new Carta(rutas[i]));
        }

        //creamos el panel donde estarán las cartas
         cartas = new JPanel();
        
        cartas.setOpaque(false);
        //cartas.setSize((100+10)*4,(100+8)*2);
        //asignamos un layout a las cartas
        int cuadrado = (int) Math.sqrt(carta.size());
        //616,height=337]
        //[width=616,height=508] 6+
        //[width=584,height=647] 8
        //cartas.setMinimumSize(new Dimension(616,647));
        cartas.setLayout(new GridLayout(cuadrado, cuadrado, HGAP, VGAP));
        //deshordenamos las cartas
        Collections.shuffle(carta);
        
        for (int i = 0; i < carta.size(); i++) {
             carta.get(i).setName("" + i);
        }
        //añadimos todas las cartas y les ponemos escuchador
        for (int i = 0; i < carta.size(); i++) {
            cartas.add(carta.get(i));
            carta.get(i).addMouseListener(controlador);
        }
        //cartas.setVisible(true);
        //cartas.setBackground(Color.red);
        constrain.gridx = 0; // El área de texto empieza en la columna cero.
        constrain.gridy = 1; // El área de texto empieza en la fila cero
        constrain.gridwidth = 2; // El área de texto ocupa dos columnas.
        constrain.gridheight = 1; // El área de texto ocupa 2 filas.
        constrain.fill= GridBagConstraints.NONE;
        // constrain.anchor=   GridBagConstraints.LINE_END;
        constrain.weighty = 0.0;
        this.add(cartas, constrain);

    }


    private void empezarReloj() {
        tReloj = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contadorSeg++;
                lReloj.setText("" + contadorSeg);
                //poner si pasa de una cifra se coloque bien, 10 100 1000....
                repaint();
            }
        });
    }


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


    private void continuar() {
        bContinuar = new JButton(new ImageIcon("src/img/flechaRect.png"));
        bContinuar.setContentAreaFilled(false);
        bContinuar.setBorder(null);
        constrain.gridx = 2;
        constrain.gridy = 2;
        constrain.fill = GridBagConstraints.HORIZONTAL;
        constrain.weighty = 0.5;
        this.add(bContinuar, constrain);
        cambiarEstadoBoton("continuar", false);
        bContinuar.setActionCommand("continuar");
        bContinuar.addKeyListener(controlador);
        bContinuar.addMouseListener(controlador);
    }


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

    //auxiliar, para probar
    boolean a = true;
    public void algo() {
        if (a) {
            carta.get(3).animarSalir();
            a = false;
        } else {
            carta.get(3).animarEntrar();
            a = true;
        }
    }


    /*
     private void resize(){
     this.addComponentListener(new java.awt.event.ComponentAdapter() {
     public void componentResized(java.awt.event.ComponentEvent evt) {
     System.out.println(evt.getComponent());
     for (Carta carta1 : carta) {
     System.out.println(getWidth()+"---"+getHeight());
     carta1.actualizarTamaño();
     }
     }
     });
     }
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon("src/img/fondo.gif").getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
    
    
    public void cambiarEstadoBoton(String boton,boolean estado){
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
                System.out.println("error cambiarEstadoBoton"+boton);
                
        }
        bContinuar.setEnabled(!bContinuar.isEnabled());
    }
    public void gestionarContador(String accion){
        if(tReloj==null){
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
                System.out.println("parada");
                tReloj.stop();
                break;
                
            case "playpause":
                if(tReloj.isRunning()){
                    tReloj.stop();
                }else
                    tReloj.start();
                tReloj.setRepeats(true);
                break;
                
            default:
                System.out.println("error gestionar contador"+accion.toLowerCase());
        }
    }
    public int algunaVisible(){
        System.out.println(cartas.getSize());
        for(int i=0;i<carta.size(); i++) {
            if(carta.get(i).isSale()){
                return i;
            }
        }
        return -1;
    }
    public void girar(int i){
        carta.get(i).animar();
    }
    public boolean mismaImagen(int i,int j){
        return (carta.get(i).getUrl().equals( carta.get(j).getUrl()));
    }
    public void bloquearImagenes(int i, int j){
        carta.get(i).bloquear();
        carta.get(j).bloquear();
        desactivadas+=2;
    }
    public void movimiento(){
        contMov++;
        lMovimientos.setText("Movimientos: " + contMov);
    }
    public boolean isFin(){
        System.out.println("carta.size()"+carta.size()+"   desactivadas "+desactivadas+"   =========="+(carta.size()>desactivadas));
        return carta.size()>desactivadas;
       //Carta.getActivadas();
    }
}
