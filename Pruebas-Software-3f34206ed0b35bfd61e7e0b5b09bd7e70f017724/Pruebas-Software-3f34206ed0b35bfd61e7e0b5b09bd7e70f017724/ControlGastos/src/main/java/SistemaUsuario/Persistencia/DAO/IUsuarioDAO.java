/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SistemaUsuario.Persistencia.DAO;

import SistemaUsuario.Persistencia.Entidades.Usuarios;

/**
 *
 * @author delll
 */
public interface IUsuarioDAO {
    
    public void AgregarUsuario(Usuarios usuario);
    public long obtenerIDusuario(String nombre,String contra);
    public Usuarios obtenerSoloUusario(long id);
    public long usuarioExistente(String nombre);
}
