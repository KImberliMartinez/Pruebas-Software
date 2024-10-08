/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaUsuario.Negocio;

/**
 *
 * @author delll
 */
public class UsuariosDTO {
    long usuarioId;
    String nombreUsuario;
    String contra;

    public UsuariosDTO(String nombreUsuario, String contra) {
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
}
