/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaUsuario.Negocio;

/**
 *
 * @author delll
 */
public interface IConsultaUsuario {
    public void AgregarUsuario(UsuariosDTO usuario);
    public long obtenerIDusuario(String nombre,String contra);
    public long usuarioExistente(String nombre);
    
}
