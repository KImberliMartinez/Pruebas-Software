/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaReporte.Negocio;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Arell
 */
public class Pintar {
private ImageIcon imagen;
private Icon foto;

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public Icon getFoto() {
        return foto;
    }

    public void setFoto(Icon foto) {
        this.foto = foto;
    }

    public Pintar() {
    }

    public void PintarImagen(JLabel label,String ruta){
    this.imagen = new ImageIcon(ruta);
    this.foto = new ImageIcon(this.imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH));
    label.setIcon(this.foto);}
}
