/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroadapter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Guille
 */
public class Logica {
    private ControladorRegistro contrReg;
    private boolean estado;
    private ControladorApp contrApp;
    private String name,age;
    private ImageIcon img;
    
    private int contador=0;
    private boolean fijo=false;
    
    public Logica() {
    }
    
    public void setControladorReg(ControladorRegistro contrReg){
        this.contrReg=contrReg;
    }
    public void setControladorApp(ControladorApp contrApp){
        //this.contrReg=null;
        this.contrApp=contrApp;
    }
    
    public void accionRegistro(String accion){
       //if(RegistroActivo){
            switch (accion) {
                case "anadirFoto":
                   //contrReg.seleccioarImagen();
                   anadirImagen();
                   break;
                   
                case "imagen1":
                case "imagen2":
                case "imagen3":
                case "imagen4"://anadimos la 4 si no ha anadido ninguna no se puede selecionar, puesto que es null(el jlabel)
                    //se ha pulsado una imagen
                    char algo=accion.charAt(6);
                    seleccioanrImagen(Integer.valueOf(""+accion.charAt(6)));//hacemos un cast del ultimo numero que corresponde a la imegen
                    break;
                case "name":
                   break;
                case "age":
                   break;
                case "panel":
                    break;
                case "siguiente"://boton;
                    comprobarAcciones();
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //tmb se podria usar un item listener o un action listener para comprobar ,(DEspues de)
                    if(estado){
                        guardarDatos();
                        contrReg.crearApp();
                    }
                   break;
               default:
                   System.out.println("error en: "+accion);
           }
            comprobarAcciones();
       //} 
       
    }
    public void comprobarAcciones(){
        //System.out.println("name: "+contrReg.comprobar("Name")+" age: "+contrReg.comprobar("age")+" goto: "+contrReg.comprobar("foto"));
        estado=false;
        //System.out.println(contrReg.comprobar("name").equals("")+"--------"+contrReg.comprobar("name").equals(" "));
        //System.out.println(contrReg.comprobar("age"));
        if(!contrReg.comprobar("name").equals("")){//si el nombre es diferente de "" (si hay algo)
            if(!contrReg.comprobar("age").equals(" ")){//si la edad es diferente de "" (si hay algo)
                if(!contrReg.comprobar("foto").equals("0")){//si hay alguna foto seleccinada
                    estado=true;
                }
            }
        }
        contrReg.estadoBoton(estado);
    }

    public void anadirImagen() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG", "jpg", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(fc);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            seleccioanrImagen(contrReg.anadirImagen(fc.getSelectedFile().getPath()));//anádimos la imagen y la seleccionamos
        }
    }
    public void seleccioanrImagen(int numero){
        contrReg.deseleccionarImagen();//delecionamois siempre toadas antes de seleccionar
        contrReg.seleccioarImagen(numero);
    }
    private void guardarDatos(){
        name=contrReg.comprobar("name");
        age=contrReg.comprobar("age");
        int nImagen= Integer.valueOf(contrReg.comprobar("foto"));
        img=contrReg.getImagen(nImagen);
    }
    public ImageIcon getImagen(){
        return img;
    }
    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    
    
    public void accionApp(String accion){
        switch (accion) {
            case "entra":
                if(fijo){
                    
                }else{
                   contrApp.cambiarEstado(true); 
                }
                break;
            case "sale":
                if(fijo){
                    
                }else{
                   contrApp.cambiarEstado(false); 
                }
                break;
            case "boton":
                contrApp.pulsoBoton();
                break;
            default:
                System.out.println("error");
        }
    }
    public void tecla(char tecla){
        if(tecla =='a' || tecla =='A'){
            contador++;
            if(contador>=3){
                fijo=true;
                contrApp.cambiarEstado(true);
            }
        }
    }
    
}
