/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajodi;


import java.awt.Color;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 *
 * @author JesusClase
 */
public class Metodos {

    public ImageIcon imagenEspejo(String ruta) {
        //BufferedImage for source image
        BufferedImage simg = null;
        //File object
        File f = null;

        //read source image file
        try {
            f = new File("src" + ruta);//necesita la carpera src para enontrar la ruta
            simg = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        //guardamos los tamaños de la imagen
        int width = simg.getWidth();
        int height = simg.getHeight();

        //Creamos el BufferedImage con las simensiones de la imagen
        BufferedImage mimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //Creamos la imagen "espejo"
        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                //lx starts from the left side of the image
                //rx starts from the right side of the image
                //get source pixel value
                int p = simg.getRGB(lx, y);
                //set mirror image pixel value - both left and right
                //mimg.setRGB(lx, y, p);
                mimg.setRGB(rx, y, p);
            }
        }

        return new ImageIcon(mimg.getScaledInstance(mimg.getWidth(), mimg.getHeight(), SCALE_SMOOTH));
    }


    /**
     * Proporciona un color aleatorio para el panel
     * @return Devuelve un color
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
     * Modifica el tamaño de los iconos
     *
     * @param icono
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public ImageIcon cambiarTamano(ImageIcon icono, int anchoImagen, int altoImagen) {
        return new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH));
    }


    /**
     * Modifica el tamaño de las imagenes
     *
     * @param imagen
     * @param anchoImagen
     * @param altoImagen
     * @return
     */
    public Image cambiarTamano(Image icono, int anchoImagen, int altoImagen) {
        return icono.getScaledInstance(anchoImagen, altoImagen, java.awt.Image.SCALE_SMOOTH);
    }
}
